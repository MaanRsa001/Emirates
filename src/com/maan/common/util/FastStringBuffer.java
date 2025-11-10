
package com.maan.common.util;

import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.StackObjectPool;

/**
 * <p>A fast string buffer implements a mutable sequence of characters.
 * </p>
 * <p>Fast string buffers are unsafe for use by multiple threads.
 * </p>
 * <p>Every string buffer has a capacity. As long as the length of the
 * character sequence contained in the string buffer does not exceed
 * the capacity, it is not necessary to allocate a new internal
 * buffer array. If the internal buffer overflows, it is
 * automatically made larger.</p>
 * <p>There are several performance improvements that make it worthwhile to
 * use this class over the standard JDK <code>StringBuffer</code>
 * <ul>
 * <li>Method calls are unsynchronized.  Calls to synchronized methods are
 * automatically at least four times slower than unsynchronized methods</li>
 * <li>Pooling.  By using the pooling mechanism, you basically remove all
 * string allocation overhead and the fast string buffer is most likely
 * pre-allocated to a size you'll use</li>
 * <li>Non-zeroing out.  <code>StringBuffer</code> zero's out it's character array,
 * even though it's not necessary. FastStringBuffer doesn't perform any memory
 * zeroing<li>
 * <li>Parameter Checks removed. FastStringBuffer doesn't perform any out of bounds
 * checks against incoming strings and parameters.  This automatically saves overhead
 * at the expense of you potentially getting less descriptive error messages</li>
 * <li>Reuse.  By calling the <code>clear()</code> method you can reuse a FastStringBuffer
 * thus saving yourself a lot of memory allocations</li>
 * </ul>
 *
 * @see     java.io.ByteArrayOutputStream
 * @see     java.lang.StringBuffer
 * @deprecated To be moved.  Use: rating.FastStringBuffer instead
 */
public final class FastStringBuffer
        implements java.io.Serializable {

    /** The value is used for character storage. */
    private char[] value;

    /** The count is the number of characters in the buffer. */
    private int count;

    /** A flag indicating whether the buffer is shared */
    private boolean shared;

    static FastStringBuffer pointerInstance = null;
    private static FastStringBufferObjectFactory factory = null;
    private static ObjectPool thePool = null;

    private static Object objectLock = new Object();


    /** use serialVersionUID from JDK 1.0.2 for interoperability */
    // static final long serialVersionUID = 3388685877147921107L;
    /**
     * Constructs a string buffer with no characters in it and an
     * initial capacity of 16 characters.
     *
     */
    public FastStringBuffer() {
        this(16);
    }

    /**
     * Constructs a string buffer with no characters in it and an
     * initial capacity specified by the <code>length</code> argument.
     *
     * @param      length   the initial capacity.
     * @exception  NegativeArraySizeException  if the <code>length</code>
     *               argument is less than <code>0</code>.
     */
    public FastStringBuffer(int length) {
        synchronized (objectLock) {
            if (pointerInstance.factory == null)
                pointerInstance.factory = new FastStringBuffer.FastStringBufferObjectFactory();
        }
        value = new char[length];
        shared = false;
    }

    /**
     * Constructs a string buffer so that it represents the same
     * sequence of characters as the string argument. The initial
     * capacity of the string buffer is <code>16</code> plus the length
     * of the string argument.
     *
     * @param   str   the initial contents of the buffer.
     */
    public FastStringBuffer(String str) {
        this(str.length() + 16);
        append(str);

        synchronized (objectLock) {
            if (pointerInstance.factory == null)
                pointerInstance.factory = new FastStringBuffer.FastStringBufferObjectFactory();
        }

    }

    /**
     * Returns the length (character count) of this string buffer.
     *
     * @return  the number of characters in this string buffer.
     */
    public int length() {
        return count;
    }

    /**
     * Returns the current capacity of the String buffer. The capacity
     * is the amount of storage available for newly inserted
     * characters; beyond which an allocation will occur.
     *
     * @return  the current capacity of this string buffer.
     */
    public int capacity() {
        return value.length;
    }

    /**
     * Clears the buffer and prepares it for reuse.
     */
    public void clear() {
        copyWhenShared();
        count = 0;
    }

    /**
     * Copies the buffer value if it is shared.
     */
    private final void copyWhenShared() {
        if (shared) {
            char[] newValue = new char[value.length];
            System.arraycopy(value, 0, newValue, 0, count);
            value = newValue;
            shared = false;
        }
    }

    /**
     * Ensures that the capacity of the buffer is at least equal to the
     * specified minimum.
     * If the current capacity of this string buffer is less than the
     * argument, then a new internal buffer is allocated with greater
     * capacity. The new capacity is the larger of:
     * <ul>
     * <li>The <code>minimumCapacity</code> argument.
     * <li>Twice the old capacity, plus <code>2</code>.
     * </ul>
     * If the <code>minimumCapacity</code> argument is nonpositive, this
     * method takes no action and simply returns.
     *
     * @param   minimumCapacity   the minimum desired capacity.
     */
    public void ensureCapacity(int minimumCapacity) {
        int maxCapacity = value.length;

        if (minimumCapacity > maxCapacity) {
            int newCapacity = maxCapacity + maxCapacity + 2;

            if (minimumCapacity > newCapacity) {
                newCapacity = minimumCapacity;
            }

            char[] newValue = new char[newCapacity];
            System.arraycopy(value, 0, newValue, 0, count);
            value = newValue;
            shared = false;
        }
    }

    /**
     * Sets the length of this String buffer.
     * If the <code>newLength</code> argument is less than the current
     * length of the string buffer, the string buffer is truncated to
     * contain exactly the number of characters given by the
     * <code>newLength</code> argument.
     * <p>
     * If the <code>newLength</code> argument is greater than or equal
     * to the current length, sufficient null characters
     * (<code>'&#92;u0000'</code>) are appended to the string buffer so that
     * length becomes the <code>newLength</code> argument.
     * <p>
     * The <code>newLength</code> argument must be greater than or equal
     * to <code>0</code>.
     *
     * @param      newLength   the new length of the buffer.
     * @see        java.lang.StringBuffer#length()
     */
    public void setLength(int newLength) {
        ensureCapacity(newLength);

        if (count < newLength) {
            copyWhenShared();

//            for (; count < newLength; count++) {
//                value[count] = '\0';
//            }
        }

        count = newLength;
    }

    /**
     * Returns the character at a specific index in this string buffer.
     * <p>
     * The first character of a string buffer is at index
     * <code>0</code>, the next at index <code>1</code>, and so on, for
     * array indexing.
     * <p>
     * The index argument must be greater than or equal to
     * <code>0</code>, and less than the length of this string buffer.
     *
     * @param      index   the index of the desired character.
     * @return     the character at the specified index of this string buffer.
     * @see        java.lang.StringBuffer#length()
     */
    public char charAt(int index) {
        return value[index];
    }

    /**
     * Characters are copied from this string buffer into the
     * destination character array <code>dst</code>. The first character to
     * be copied is at index <code>srcBegin</code>; the last character to
     * be copied is at index <code>srcEnd-1.</code> The total number of
     * characters to be copied is <code>srcEnd-srcBegin</code>. The
     * characters are copied into the subarray of <code>dst</code> starting
     * at index <code>dstBegin</code> and ending at index:
     * <p><blockquote><pre>
     *     dstbegin + (srcEnd-srcBegin) - 1
     * </pre></blockquote>
     *
     * @param      srcBegin   start copying at this offset in the string buffer.
     * @param      srcEnd     stop copying at this offset in the string buffer.
     * @param      dst        the array to copy the data into.
     * @param      dstBegin   offset into <code>dst</code>.
     * @exception  StringIndexOutOfBoundsException  if there is an invalid
     *               index into the buffer.
     */
    public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) {
        System.arraycopy(value, srcBegin, dst, dstBegin, srcEnd -
                srcBegin);
    }

    /**
     * The character at the specified index of this string buffer is set
     * to <code>ch</code>.
     * <p>
     * The offset argument must be greater than or equal to
     * <code>0</code>, and less than the length of this string buffer.
     *
     * @param      index   the index of the character to modify.
     * @param      ch      the new character.
     * @exception  StringIndexOutOfBoundsException  if the index is invalid.
     * @see        java.lang.StringBuffer#length()
     */
    public void setCharAt(int index, char ch) {
        copyWhenShared();
        value[index] = ch;
    }

    /**
     * Appends the string representation of the <code>Object</code>
     * argument to this string buffer.
     * <p>
     * The argument is converted to a string as if by the method
     * <code>String.valueOf</code>, and the characters of that
     * string are then appended to this string buffer.
     *
     * @param   obj   an <code>Object</code>.
     * @return  this string buffer.
     * @see     java.lang.String#valueOf(java.lang.Object)
     * @see     java.lang.StringBuffer#append(java.lang.String)
     */
    public FastStringBuffer append(Object obj) {
        return append(String.valueOf(obj));
    }

    /**
     * Appends the string to this string buffer.
     * <p>
     * The characters of the <code>String</code> argument are appended, in
     * order, to the contents of this string buffer, increasing the
     * length of this string buffer by the length of the argument.
     *
     * @param   str   a string.
     * @return  this string buffer.
     */
    public FastStringBuffer append(String str) {
        if (str == null) {
            str = String.valueOf(str);
        }

        int len = str.length();
        ensureCapacity(count + len);
        copyWhenShared();
        str.getChars(0, len, value, count);
        count += len;

        return this;
    }

    /**
     * Appends the string representation of the <code>char</code> array
     * argument to this string buffer.
     * <p>
     * The characters of the array argument are appended, in order, to
     * the contents of this string buffer. The length of this string
     * buffer increases by the length of the argument.
     *
     * @param   str   the characters to be appended.
     * @return  this string buffer.
     */
    public FastStringBuffer append(char[] str) {
        int len = str.length;
        ensureCapacity(count + len);
        copyWhenShared();
        System.arraycopy(str, 0, value, count, len);
        count += len;

        return this;
    }

    /**
     * Appends the string representation of a subarray of the
     * <code>char</code> array argument to this string buffer.
     * <p>
     * Characters of the character array <code>str</code>, starting at
     * index <code>offset</code>, are appended, in order, to the contents
     * of this string buffer. The length of this string buffer increases
     * by the value of <code>len</code>.
     *
     * @param   str      the characters to be appended.
     * @param   offset   the index of the first character to append.
     * @param   len      the number of characters to append.
     * @return  this string buffer.
     */
    public FastStringBuffer append(char[] str, int offset, int len) {
        ensureCapacity(count + len);
        copyWhenShared();
        System.arraycopy(str, offset, value, count, len);
        count += len;

        return this;
    }

    /**
     * Appends one FastStringBuffer to another so they can be merged with no
     * unnecessary allocations
     *
     * @return this string buffer
     */
    public FastStringBuffer append(FastStringBuffer str) {
        int len = str.length();
        ensureCapacity(count + str.count);
        copyWhenShared();
        System.arraycopy(str.getValue(), 0, value, count, len);
        count += len;

        return this;
    }

    /**
     * Appends the string representation of the <code>boolean</code>
     * argument to the string buffer.
     * <p>
     * The argument is converted to a string as if by the method
     * <code>String.valueOf</code>, and the characters of that
     * string are then appended to this string buffer.
     *
     * @param   b   a <code>boolean</code>.
     * @return  this string buffer.
     * @see     java.lang.String#valueOf(boolean)
     * @see     java.lang.StringBuffer#append(java.lang.String)
     */
    public FastStringBuffer append(boolean b) {
        return append(String.valueOf(b));
    }

    /**
     * Appends the string representation of the <code>char</code>
     * argument to this string buffer.
     * <p>
     * The argument is appended to the contents of this string buffer.
     * The length of this string buffer increases by <code>1</code>.
     *
     * @param   ch   a <code>char</code>.
     * @return  this string buffer.
     */
    public FastStringBuffer append(char c) {
        ensureCapacity(count + 1);
        copyWhenShared();
        value[count++] = c;

        return this;
    }

    /**
     * Appends the string representation of the <code>int</code>
     * argument to this string buffer.
     * <p>
     * The argument is converted to a string as if by the method
     * <code>String.valueOf</code>, and the characters of that
     * string are then appended to this string buffer.
     *
     * @param   i   an <code>int</code>.
     * @return  this string buffer.
     * @see     java.lang.String#valueOf(int)
     * @see     java.lang.StringBuffer#append(java.lang.String)
     */
    public FastStringBuffer append(int i) {
        return append(String.valueOf(i));
    }

    /**
     * Appends the string representation of the <code>long</code>
     * argument to this string buffer.
     * <p>
     * The argument is converted to a string as if by the method
     * <code>String.valueOf</code>, and the characters of that
     * string are then appended to this string buffer.
     *
     * @param   l   a <code>long</code>.
     * @return  this string buffer.
     * @see     java.lang.String#valueOf(long)
     * @see     java.lang.StringBuffer#append(java.lang.String)
     */
    public FastStringBuffer append(long l) {
        return append(String.valueOf(l));
    }

    /**
     * Appends the string representation of the <code>float</code>
     * argument to this string buffer.
     * <p>
     * The argument is converted to a string as if by the method
     * <code>String.valueOf</code>, and the characters of that
     * string are then appended to this string buffer.
     *
     * @param   f   a <code>float</code>.
     * @return  this string buffer.
     * @see     java.lang.String#valueOf(float)
     * @see     java.lang.StringBuffer#append(java.lang.String)
     */
    public FastStringBuffer append(float f) {
        return append(String.valueOf(f));
    }

    /**
     * Appends the string representation of the <code>double</code>
     * argument to this string buffer.
     * <p>
     * The argument is converted to a string as if by the method
     * <code>String.valueOf</code>, and the characters of that
     * string are then appended to this string buffer.
     *
     * @param   d   a <code>double</code>.
     * @return  this string buffer.
     * @see     java.lang.String#valueOf(double)
     * @see     java.lang.StringBuffer#append(java.lang.String)
     */
    public FastStringBuffer append(double d) {
        return append(String.valueOf(d));
    }

    /**
     * The character sequence contained in this string buffer is
     * replaced by the reverse of the sequence.
     *
     * @return  this string buffer.
     */
    public FastStringBuffer reverse() {
        copyWhenShared();

        int n = count - 1;

        for (int j = (n - 1) >> 1; j >= 0; --j) {
            char temp = value[j];
            value[j] = value[n - j];
            value[n - j] = temp;
        }

        return this;
    }

    /**
     * Converts to a string representing the data in this string buffer.
     * A new <code>String</code> object is allocated and initialized to
     * contain the character sequence currently represented by this
     * string buffer. This <code>String</code> is then returned. Subsequent
     * changes to the string buffer do not affect the contents of the
     * <code>String</code>.
     *
     * @return  a string representation of the string buffer.
     */
    public String toString() {
        return new String(value, 0, count);
    }

    //
    // The following two methods are needed by String to efficiently
    // convert a StringBuffer into a String.  They are not public.
    // They shouldn't be called by anyone but String.
    final void setShared() {
        shared = true;
    }

    final char[] getValue() {
        return value;
    }


    public synchronized static FastStringBuffer getInstance() {
        synchronized (objectLock) {
            if (FastStringBuffer.pointerInstance == null) {
                pointerInstance = new FastStringBuffer(1);
//                thePool = new GenericObjectPool(pointerInstance.factory
//                        ,100,GenericObjectPool.WHEN_EXHAUSTED_GROW,0);
                thePool = new StackObjectPool(pointerInstance.factory);
            }

            try {
//                FastStringBuffer anInstance = (FastStringBuffer)thePool.borrowObject();
//                anInstance.clear();
//                if (anInstance.value.length > 1024) {
//                    anInstance.value = new char[1024];
//                }
                return (FastStringBuffer) thePool.borrowObject();

            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                ex.printStackTrace();
                return null;
            }
        }
    }

    public void release() {
        synchronized (objectLock) {
            try {
                thePool.returnObject(this);
            } catch (Exception ex) {
            }
        }
    }


    class FastStringBufferObjectFactory extends BasePoolableObjectFactory {
        // for makeObject we'll simply return a new buffer
        public Object makeObject() {
            return new FastStringBuffer(1024);
        }

        //we'll clear it out and reset it's size to 1024 if it grew
        //bigger than one k.
        public void passivateObject(Object obj) {
            FastStringBuffer buf = (FastStringBuffer) obj;
            if (buf.value.length > 1024) {
                buf.value = new char[1024];
                buf.clear();
            } else {
                buf.clear();
            }
        }

        // for all other methods, the no-op
        // implementation in BasePoolableObjectFactory
        // will suffice

    }
}