package dev.facturador;

import antlr.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;

@SpringBootApplication
public class FacturadorMasMasApplication {

    public static void main(String... args) {
        String str = "gfgfrgadw";
        str =modifyString(str);
        
        SpringApplication.run(FacturadorMasMasApplication.class, args);
    }

    public static String modifyString(String str){
        int length = str.length();
        for(int i = 0; i < length; i++){
            if(Character.compare(str.charAt(i), str.toUpperCase().charAt(i)) == 0){
                switch(str.charAt(i)){
                    case 'G': str =str.replaceFirst("G", "A"); break;
                    //...
                    default: break;
                }
            }
            if(Character.compare(str.charAt(i), str.toLowerCase().charAt(i)) != 0){
                switch(str.charAt(i)){
                    case 'g': str =str.replaceFirst("g", "a"); break;
                    //...
                    default: break;
                }
            }
        }
        return str;
    }
}
