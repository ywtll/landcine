package Dao;

import lombok.Data;

import java.awt.*;
import java.io.File;
import java.io.InputStreamReader;

@Data
public class TheTheme {
   private Color colorFont;
   private Color colorBg;
   private Color searchColorImage;

   public TheTheme(){

      int index = 1;
      File file = new File("src/main/resources/theme.txt");
      try {
         InputStreamReader reader = new InputStreamReader(file.toURI().toURL().openStream());
         char[] chars = new char[1024];
         int len = reader.read(chars);
         String str = new String(chars, 0, len);
         // 删除前后的空格和换行符
         str = str.replaceAll("\\s*", "");
         index = Integer.parseInt(str);
      } catch (Exception e) {
         e.printStackTrace();
      }

      switch (index) {
         case 1:
            theme1();
            break;
         case 2:
            theme2();
            break;
      }
   }

   public void theme1() {
      colorFont = new Color(0, 0, 0);
      colorBg = new Color(255,255,255);
      searchColorImage = new Color(150, 150, 150);
   }

   public void theme2() {
      colorFont = new Color(255,255,255);
      colorBg = new Color(0,0,0);
      searchColorImage = new Color(145, 145, 145);
   }



}
