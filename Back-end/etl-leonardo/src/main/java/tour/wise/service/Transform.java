package tour.wise.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Transform {

    private static LocalDate transformDate(Date data) {
        return data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
