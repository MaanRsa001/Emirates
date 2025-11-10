package com.maan.Home.MasterController;


import java.util.ArrayList;
import java.util.List;
public class NumberToWord {

    static public class ScaleUnit {
        private int exponent;
        private String[] names;
        private ScaleUnit(int exponent, String...names) {
            this.exponent = exponent;
            this.names = names;
        }
        public int getExponent() {
            return exponent;
        }
        public String getName(int index) {
            return names[index];
        }
    }

    /**
     * See http://www.wordiq.com/definition/Names_of_large_numbers
     */
    static private ScaleUnit[] SCALE_UNITS = new ScaleUnit[] {
        new ScaleUnit(63, "Vigintillion", "Decilliard"),
        new ScaleUnit(60, "Novemdecillion", "Decillion"),
        new ScaleUnit(57, "Octodecillion", "Nonilliard"),
        new ScaleUnit(54, "Septendecillion", "Nonillion"),
        new ScaleUnit(51, "Sexdecillion", "Octilliard"),
        new ScaleUnit(48, "Quindecillion", "Octillion"),
        new ScaleUnit(45, "Quattuordecillion", "Septilliard"),
        new ScaleUnit(42, "Tredecillion", "Septillion"),
        new ScaleUnit(39, "Duodecillion", "Sextilliard"),
        new ScaleUnit(36, "Undecillion", "Sextillion"),
        new ScaleUnit(33, "Decillion", "Quintilliard"),
        new ScaleUnit(30, "Nonillion", "Quintillion"),
        new ScaleUnit(27, "Octillion", "Quadrilliard"),
        new ScaleUnit(24, "Septillion", "Quadrillion"),
        new ScaleUnit(21, "Sextillion", "Trilliard"),
        new ScaleUnit(18, "Quintillion", "Trillion"),
        new ScaleUnit(15, "Quadrillion", "Billiard"),
        new ScaleUnit(12, "Trillion", "Billion"),
        new ScaleUnit(9, "Billion", "Billiard"),
        new ScaleUnit(6, "Million", "Million"),
        new ScaleUnit(3, "Thousand", "Thousand"),
        new ScaleUnit(2, "Hundred", "Hundred"),
        //new ScaleUnit(1, "ten", "ten"),
        //new ScaleUnit(0, "one", "one"),
       /* new ScaleUnit(-1, "tenth", "tenth"),
        new ScaleUnit(-2, "hundredth", "hundredth"),
        new ScaleUnit(-3, "thousandth", "thousandth"),
        new ScaleUnit(-4, "ten-thousandth", "ten-thousandth"),*/
        new ScaleUnit(-1, "", ""),
        new ScaleUnit(-2, "", ""),
        new ScaleUnit(-3, "", ""),
        new ScaleUnit(-4, "", ""),
        new ScaleUnit(-5, "hundred-thousandth", "hundred-thousandth"),
        new ScaleUnit(-6, "millionth", "millionth"),
        new ScaleUnit(-7, "ten-millionth", "ten-millionth"),
        new ScaleUnit(-8, "hundred-millionth", "hundred-millionth"),
        new ScaleUnit(-9, "billionth", "milliardth"),
        new ScaleUnit(-10, "ten-billionth", "ten-milliardth"),
        new ScaleUnit(-11, "hundred-billionth", "hundred-milliardth"),
        new ScaleUnit(-12, "trillionth", "billionth"),
        new ScaleUnit(-13, "ten-trillionth", "ten-billionth"),
        new ScaleUnit(-14, "hundred-trillionth", "hundred-billionth"),
        new ScaleUnit(-15, "quadrillionth", "billiardth"),
        new ScaleUnit(-16, "ten-quadrillionth", "ten-billiardth"),
        new ScaleUnit(-17, "hundred-quadrillionth", "hundred-billiardth"),
        new ScaleUnit(-18, "quintillionth", "trillionth"),
        new ScaleUnit(-19, "ten-quintillionth", "ten-trillionth"),
        new ScaleUnit(-20, "hundred-quintillionth", "hundred-trillionth"),
        new ScaleUnit(-21, "sextillionth", "trilliardth"),
        new ScaleUnit(-22, "ten-sextillionth", "ten-trilliardth"),
        new ScaleUnit(-23, "hundred-sextillionth", "hundred-trilliardth"),
        new ScaleUnit(-24, "septillionth","quadrillionth"),
        new ScaleUnit(-25, "ten-septillionth","ten-quadrillionth"),
        new ScaleUnit(-26, "hundred-septillionth","hundred-quadrillionth"),
    };

    static public enum Scale {
        SHORT,
        LONG;

        public String getName(int exponent) {
            for (ScaleUnit unit : SCALE_UNITS) {
                if (unit.getExponent() == exponent) {
                    return unit.getName(this.ordinal());
                }
            }
            return ""; 
        }
    }

    /**
     * Change this scale to support American and modern British value (short scale)
     * or Traditional British value (long scale)
     */
    static public Scale SCALE = Scale.SHORT; 


    static abstract public class AbstractProcessor {

        static protected final String SEPARATOR = " ";
        static protected final int NO_VALUE = -1;

        protected List<Integer> getDigits(long value) {
            ArrayList<Integer> digits = new ArrayList<Integer>();
            if (value == 0) {
                digits.add(0);
            } else {
                while (value > 0) {
                    digits.add(0, (int) value % 10);
                    value /= 10;
                }
            }
            return digits;
        }

        public String getName(long value) {
            return getName(Long.toString(value));
        }

        public String getName(double value) {
            return getName(Double.toString(value));
        }

        abstract public String getName(String value);
    }

    static public class UnitProcessor extends AbstractProcessor {

        static private final String[] TOKENS = new String[] {
            "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
            "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"
        };

        @Override
        public String getName(String value) {
            StringBuilder buffer = new StringBuilder();

            int offset = NO_VALUE;
            int number;
            if (value.length() > 3) {
                number = Integer.valueOf(value.substring(value.length() - 3), 10);
            } else {
                number = Integer.valueOf(value, 10);
            }
            number %= 100;
            if (number < 10) {
                offset = (number % 10) - 1;
                //number /= 10;
            } else if (number < 20) {
                offset = (number % 20) - 1;
                //number /= 100;
            }

            if (offset != NO_VALUE && offset < TOKENS.length) {
                buffer.append(TOKENS[offset]);
            }

            return buffer.toString();
        }

    }

    static public class TensProcessor extends AbstractProcessor {

        static private final String[] TOKENS = new String[] {
            "Twenty", "Thirty", "Fourty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
        };

        static private final String UNION_SEPARATOR = "-";

        private UnitProcessor unitProcessor = new UnitProcessor();

        @Override
        public String getName(String value) {
            StringBuilder buffer = new StringBuilder();
            boolean tensFound = false;

            int number;
            if (value.length() > 3) {
                number = Integer.valueOf(value.substring(value.length() - 3), 10);
            } else {
                number = Integer.valueOf(value, 10);
            }
            number %= 100;   // keep only two digits
            if (number >= 20) {
                buffer.append(TOKENS[(number / 10) - 2]);
                number %= 10;
                tensFound = true;
            } else {
                number %= 20;
            }

            if (number != 0) {
                if (tensFound) {
                    buffer.append(UNION_SEPARATOR);
                }
                buffer.append(unitProcessor.getName(number));
            }

            return buffer.toString();
        }
    }

    static public class HundredProcessor extends AbstractProcessor {

        private int EXPONENT = 2;

        private UnitProcessor unitProcessor = new UnitProcessor();
        private TensProcessor tensProcessor = new TensProcessor();

        @Override
        public String getName(String value) {
            StringBuilder buffer = new StringBuilder();

            int number;
            if (value==null || value.length()<=0) {
                number = 0;
            } else if (value.length() > 4) {
                number = Integer.valueOf(value.substring(value.length() - 4), 10);
            } else {
                number = Integer.valueOf(value, 10);
            }
            number %= 1000;  // keep at least three digits

            if (number >= 100) {
                buffer.append(unitProcessor.getName(number / 100));
                buffer.append(SEPARATOR);
                buffer.append(SCALE.getName(EXPONENT));
            }

            String tensName = tensProcessor.getName(number % 100);

            if (tensName!= null && tensName.length() > 0 && (number >= 100)) {
                buffer.append(SEPARATOR);
            }
            buffer.append(tensName);

            return buffer.toString();
        }
    }

    static public class CompositeBigProcessor extends AbstractProcessor {

        private HundredProcessor hundredProcessor = new HundredProcessor();
        private AbstractProcessor lowProcessor;
        private int exponent;

        public CompositeBigProcessor(int exponent) {
            if (exponent <= 3) {
                lowProcessor = hundredProcessor;
            } else {
                lowProcessor = new CompositeBigProcessor(exponent - 3);
            }
            this.exponent = exponent;
        }

        public String getToken() {
            return SCALE.getName(getPartDivider());
        }

        protected AbstractProcessor getHighProcessor() {
            return hundredProcessor;
        }

        protected AbstractProcessor getLowProcessor() {
            return lowProcessor;
        }

        public int getPartDivider() {
            return exponent;
        }

        @Override
        public String getName(String value) {
            StringBuilder buffer = new StringBuilder();

            String high, low;
            if (value.length() < getPartDivider()) {
                high = "";
                low = value;
            } else {
                int index = value.length() - getPartDivider();
                high = value.substring(0, index);
                low = value.substring(index);
            }

            String highName = getHighProcessor().getName(high);
            String lowName = getLowProcessor().getName(low);

            if (highName!=null && highName.length()>0) {
                buffer.append(highName);
                buffer.append(SEPARATOR);
                buffer.append(getToken());

                if (lowName!=null && lowName.length()>0) {
                    buffer.append(SEPARATOR);
                }
            }

            if (lowName!=null &&lowName.length()>0) {
                buffer.append(lowName);
            }

            return buffer.toString();
        }
    }

    static public class DefaultProcessor extends AbstractProcessor {

        static private String MINUS = "minus";
        static private String UNION_AND = "and";

        static private String ZERO_TOKEN = "zero";

        private AbstractProcessor processor = new CompositeBigProcessor(63);

        @Override
        public String getName(String value) {
            boolean negative = false;
            if (value.startsWith("-")) {
                negative = true;
                value = value.substring(1);
            }

            int decimals = value.indexOf(".");
            String decimalValue = null;
            if (0 <= decimals) {
                decimalValue = value.substring(decimals + 1);
                value = value.substring(0, decimals);
            }

            String name = processor.getName(value);

            if (name==null || name.length()<0) {
                name = ZERO_TOKEN;
            } else if (negative) {
                name = MINUS.concat(SEPARATOR).concat(name); 
            }

            if (!(null == decimalValue || decimalValue.length()<=0)) {
                name = name.concat(SEPARATOR).concat(UNION_AND).concat(SEPARATOR)
                    .concat(processor.getName(decimalValue))
                    .concat(SEPARATOR).concat(SCALE.getName(-decimalValue.length()));
            }

            return name;
        }

    }

    static public AbstractProcessor processor;


    public static void main(String...args) {

        processor = new DefaultProcessor();

       /* long[] values = new long[] {
            0,
            4,
            10,
            12,
            100,
            108,
            299,
            1000,
            1003,
            2040,
            45213,
            100000,
            100005,
            100010,
            202020,
            202022,
            999999,
            1000000,
            1000001,
            10000000,
            10000007,
            99999999,
            Long.MAX_VALUE,
            Long.MIN_VALUE
        };*/

        String[] strValues = new String[] {    "900000","900123","910000","9100000","91000000","91001231","910001231","910111231","910111000",     
            "10000000000.25"
        };
        

      /*  for (long val : values) {
            System.out.println(val + " = " + processor.getName(val) );
        }*/

        for (String strVal : strValues) {
            System.out.println(strVal + " = " + processor.getName(strVal) );
        }

        // generate a very big number...
       /* StringBuilder bigNumber = new StringBuilder();
        for (int d=0; d<66; d++) {
            bigNumber.append( (char) ((Math.random() * 10) + '0'));
        }
        bigNumber.append(".");
        for (int d=0; d<26; d++) {
            bigNumber.append( (char) ((Math.random() * 10) + '0'));
        }*/

       // System.out.println(bigNumber.toString() + " = " + processor.getName(bigNumber.toString()));

    }

}
