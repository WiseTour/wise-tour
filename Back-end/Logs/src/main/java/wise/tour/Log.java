package wise.tour;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {
    private static final Logger logger = LoggerFactory.getLogger(Log.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    public void showLog(String msg, String msgType)
    {
        String dataHora = LocalDateTime.now().format(formatter);
        String mensagemFormatada = dataHora + " - " + msg;

        switch (msgType.toUpperCase())
        {
            case "INFO":
                logger.info(mensagemFormatada);
                break;
            case "WARNING":
                logger.warn(mensagemFormatada);
                break;
            case "ERROR":
                logger.error(mensagemFormatada);
                break;
            case "DEBUG":
                logger.debug(mensagemFormatada);
                break;
            default:
                logger.info(dataHora + "UNKNOWN: " + msg);
                break;
        }
    }
}
