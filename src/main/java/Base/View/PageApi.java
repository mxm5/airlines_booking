package Base.View;

//import Domain.Tweet;

import Domain.Ticket;

import java.sql.Date;
import java.time.LocalDateTime;

public interface PageApi {

    <T> void print(T value);
    <T> void printTab(T value);

    void print();

    void line();

    void line75();

    void line(int i);

    void jump();

    String enterValue(String msg, int minimumLength);

    String enterLine(String msg );

    String enterLine280(String msg);

    String enterValue(String msg);

    void printTicketInFormat(Ticket ticket);

    LocalDateTime enterTicketDateTime() throws Exception;


    int setPrice();

    int selectOpt(int maxOpt);

    int selectOpt(int maxOpt, String message);

    int selectOpt(int maxOpt, int minOpt) throws Exception;

    <E> void printTitle(E value);

    void printTitle();


    <K> void warning(K warningMessage);

    void warning();

    <Z> void success(Z warningMessage);

    void success();

//    String formatTweet(Tweet tweet);

    int printOptions(String ... options);

    void exit();

    String enterPassword();

    void printTimeFormat(LocalDateTime localDateTime);
    void printTimeFormat(Date Date);

}
