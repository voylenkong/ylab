package local.vng.lesson05.messagefilter.service;

/**
 * Service
 *
 * @author VoylenkoNG
 * 02.04.2023
 */
public interface CensorService {

    /**
     * Цензурирование нецензурных слов по типу
     * F**k you, уважаемый!
     *
     * @param inputSentence Входящее соббщение
     * @return Цензурируемое сообщение
     */
    String censorSentence(String inputSentence);
}
