package helpers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormat {

    private String dateFormat = "dd/MM/yyyy HH:mm:ss";
    private String locale = "pt-BR";
    private SimpleDateFormat sdf = null;

    public DateFormat(){

        //Locale testeLocale = new Locale(this.locale)
        // e ai colocaria o `testeLocale` ao invés de `new Locale(this.locale)` na linha de baixo
        this.sdf = new SimpleDateFormat(this.dateFormat, new Locale(this.locale));
    }


    public String getDateFormat(){
        return this.sdf.format(new Date());
    }

}
