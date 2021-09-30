package Base.View;

//import Domain.Tweet;

import Domain.Ticket;
import Util.TimeUtil;
import org.apache.commons.lang3.StringUtils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static Util.TimeUtil.*;

public class View implements PageApi {

    {
        printTitle();
    }


    public View() {
    }

    public SimpleDateFormat sdf =
            new SimpleDateFormat("yyyy-MM-dd hh:mm");

//    @Override
//    public String formatTweet(Tweet tweet) {
//        StringBuilder formattedTweet = new StringBuilder();
//        String[] twt = tweet.getTweetText().split(" ");
//        for (int i = 0; i < twt.length; i++) {
//            formattedTweet.append(twt[i]);
//            if (i % 7 == 0 && i != 0)
//                formattedTweet.append("\n");
//            else
//                formattedTweet.append(" ");
//        }
//        return formattedTweet.toString();
//    }


    @Override
    public int printOptions(String... options) {
        int k = 0;
        for (Object opt : options) {
            System.out.println(++k + ". " + opt.toString());
        }
        return k;
    }


    @Override
    public <T> void print(T value) {
        System.out.println(value);
    }

    @Override
    public <T> void printTab(T value) {
        System.out.println("\t" + value);
    }

    @Override
    public void print() {
        System.out.println();
    }

    @Override
    public void line() {
        System.out.println("_".repeat(100));
    }

    @Override
    public void line75() {
        System.out.println("_".repeat(75));
    }

    @Override
    public void line(int i) {
        System.out.println("_".repeat(i));
    }

    @Override
    public void jump() {
        System.out.println("\n".repeat(4));

    }

    @Override
    public String enterValue(String msg, int minimumLength) {
        String val;
        System.out.print(msg + " > ");
        while (true) {
            val = new Scanner(System.in).next().strip();
            if (val.length() >= minimumLength)
                break;
            else
                warning("entered value must have at least " + minimumLength + " characters");
        }

        return val;
    }

    @Override
    public void exit() {
        printTitle("good bye");
        print("exiting ...");
    }

    @Override
    public String enterPassword() {
        String password;
        while (true) {
            password = enterValue("enter your your password", 3);
            String confirm = enterValue("confirm your password", 3);
            if (password.equals(confirm)) break;
        }
        return password;
    }

    @Override
    public String enterLine(String msg) {
        String val;
        System.out.print(msg + " > ");
        val = new Scanner(System.in).nextLine();
        return val;
    }

    @Override
    public void printTimeFormat(Date Date) {

    }


    @Override
    public void printTimeFormat(LocalDateTime localDateTime) {

    }

    @Override
    public String enterLine280(String msg) {
        String val = "";
        while (true) {
            System.out.print(msg + " : " + "\n >");
            val = new Scanner(System.in).nextLine();
            if (val.length() <= 280) break;
            print("you must enter 280 characters in comments");
        }
        return val;
    }

    @Override
    public String enterValue(String msg) {
        String val;
        System.out.print(msg + " > ");
        val = new Scanner(System.in).next();
        return val;
    }


    @Override
    public void printTicketInFormat(Ticket ticket) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String move = ticket.getMovingDate().format(formatter);
        String arrive = ticket.getArrivingDate().format(formatter);
        int size = 50;
        System.out.print(StringUtils.rightPad("from:", size, " . ")+"\n");
        System.out.println(StringUtils.leftPad(ticket.getHome(), size));
        System.out.print(StringUtils.rightPad("to:", size, " . ")+"\n");
        System.out.println(StringUtils.leftPad(ticket.getDestination(), size));
        System.out.print(StringUtils.rightPad("moving date:", size, " . ")+"\n");
        System.out.println(StringUtils.leftPad(move, size));
        System.out.print(StringUtils.rightPad("arriving date:", size, " . ")+"\n");
        System.out.println(StringUtils.leftPad(arrive, size));
        if (ticket.getOwner() == null) {
            System.out.print(StringUtils.rightPad("available for sale:", size, " . ")+"\n");
            System.out.println(StringUtils.leftPad("available +", size));
        }
        System.out.print(StringUtils.rightPad("price:", size, " . ")+"\n");
        System.out.println(StringUtils.leftPad(String.valueOf(ticket.getPrice()), size));
        System.out.print(StringUtils.rightPad("provider:", size, " . ")+"\n");
        System.out.println(StringUtils.leftPad(String.valueOf(ticket.getProviderCompany().getBrandName()), size));
        line(size);
    }

    @Override
    public LocalDateTime enterTicketDateTime() throws Exception {
        LocalDateTime selectedDateTime = null;
        // stack overflow way of getting time
        // https://stackoverflow.com/questions/23491255/read-date-in-java-with-scanner
        print("enter moving date time .");
        while (true) {
            int currentYear = nowToSqlDate().toLocalDate().getYear();
            System.out.println("enter valid year .");
            int year = selectOpt(currentYear, currentYear);
            System.out.println("enter valid month .");
            int month = selectOpt(1, 12);
            System.out.println("enter valid day .");
            int day = selectOpt(1, 31);
            System.out.println("enter valid minute .");
            int minute = selectOpt(0, 59);
            System.out.println("enter valid hour .");
            int hour = selectOpt(0, 23);
            try {
                selectedDateTime = LocalDateTime.of(year, month, day, hour, minute);
                break;
            } catch (Exception e) {
                warning("select a valid time");
            }
        }
        return selectedDateTime;
    }

    @Override
    public int setPrice() {
        int price;
        print("enter a correct value for price >");
        while (true) {
            try {
                price = new Scanner(System.in).nextInt();
                break;
            } catch (Exception e) {
                warning("insert correct value");
            }
        }
        success("entered " + price);
        return price;
    }

    @Override
    public int selectOpt(int maxOpt) {

        int opt;
        print("enter an option between 1 to " + maxOpt + " >");
        while (true) {
            try {
                opt = new Scanner(System.in).nextInt();
                if (opt > maxOpt || opt < 1)
                    warning("enter an option between 1 to " + maxOpt + " >");

                else break;
            } catch (Exception e) {

                warning("insert correct value");
            }
        }
        success("selected " + opt);
        return opt;
    }

    @Override
    public int selectOpt(int maxOpt, String message) {
        print(message + " .");
        int opt;
        print("enter an option between 1 to " + maxOpt + " >");
        while (true) {
            try {
                opt = new Scanner(System.in).nextInt();
                if (opt > maxOpt || opt < 1)
                    warning("enter an option between 1 to " + maxOpt + " >");

                else break;
            } catch (Exception e) {

                warning("insert correct value");
            }
        }
        success("selected " + opt);
        return opt;
    }

    @Override
    public int selectOpt(int minOpt, int maxOpt) throws Exception {

        if (minOpt > maxOpt) throw new Exception(" min is higher than max");
        int opt;
        print("enter an option between " + minOpt + " to " + maxOpt + " >");
        while (true) {
            try {
                opt = new Scanner(System.in).nextInt();
                if (opt > maxOpt || opt < minOpt)
                    warning("enter an option between " + minOpt + " to " + maxOpt + " >");
                else {
                    break;
                }
            } catch (Exception e) {

                warning("insert correct value");
            }
        }
        success("selected " + opt);
        return opt;
    }


    @Override
    public <E> void printTitle(E value) {
        int l = value.toString().length();
        boolean even = l % 2 == 0;
        int dist;
        if (even) {
            int total = 96;
            dist = (total - l) / 2;
        } else {
            dist = (95 - l) / 2;

        }

        line();
        String k = " ";
        System.out.println("||" + " ".repeat(dist) + value + " ".repeat(dist - 1) + (even ? " " : "  ") + "||");
        print("||" + "_".repeat(96) + "||");
        print();
    }

    @Override
    public void printTitle() {
        String className = getClass().getName();
        String[] classNArr = className.split("\\.");
        className = classNArr[classNArr.length - 1];
        printTitle(className);

    }

    @Override
    public <K> void warning(K warningMessage) {
        System.out.println("[ Err ]: " + warningMessage);
    }

    @Override
    public void warning() {
        System.out.println(" [ Err ] ");

    }

    @Override
    public <Z> void success(Z warningMessage) {
        System.out.println("[ Ok ]: " + warningMessage);
    }

    @Override
    public void success() {
        System.out.println(" [ Ok ] ");
    }


}
