package Dao;

import lombok.Data;

import java.awt.*;

@Data
public class TheTheme {
   private Color colorFont;
   private Color colorBg;
   private Color searchColorFont;
   private Color searchColorBg;

   public TheTheme(int index) {
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
      colorFont = new Color(0,0,0);
      colorBg = new Color(255,255,255);
   }

   public void theme2() {
      colorFont = new Color(255,255,255);
      colorBg = new Color(0,0,0);
   }



}
