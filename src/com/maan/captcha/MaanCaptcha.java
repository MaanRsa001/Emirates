package com.maan.captcha;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.SystemUtils;

import nl.captcha.Captcha;
import nl.captcha.gimpy.DropShadowGimpyRenderer;
import nl.captcha.servlet.CaptchaServletUtil;
import nl.captcha.text.renderer.DefaultWordRenderer;

public class MaanCaptcha extends HttpServlet {
	
	protected void doGet(final HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Color> colors = new ArrayList<Color> ();
        colors.add(Color.black);
        colors.add(Color.orange);
        colors.add(Color.BLUE);
        
        List<Font> fonts = new ArrayList<Font>();
        fonts.add(new Font("Arial", Font.BOLD, 35));
        
        Captcha captcha=null;
		try {
			captcha = new Captcha.Builder(120, 50)			
			        .addText(new DefaultWordRenderer(colors, fonts))
			     //   .gimp(new DropShadowGimpyRenderer(150,100))
			        .build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
        // display the image produced
        CaptchaServletUtil.writeImage(response, captcha.getImage());
 
        // save the captcha value on session
        request.getSession().setAttribute("simpleCaptcha", captcha);
    }
	public static String getApplicationPath() throws Exception {
		String applicationPath = "";
		try {
			final String path=(MaanCaptcha.class).getProtectionDomain().getCodeSource().getLocation().getPath();
			if(SystemUtils.IS_OS_LINUX) {
				applicationPath = path.substring(0, path.indexOf("WEB-INF"));
			}
			else if(SystemUtils.IS_OS_WINDOWS) {
				applicationPath = path.substring(1, path.indexOf("WEB-INF"));
			}
			applicationPath = applicationPath.replaceAll("%20", " ");
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return applicationPath;
	}

}
