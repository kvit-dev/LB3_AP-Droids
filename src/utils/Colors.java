package utils;

public class Colors {
    public static final String RESET = "\033[0m";

    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String BLUE = "\033[0;34m";
    public static final String CYAN = "\033[0;36m";

    public static final String RED_BOLD = "\033[1;31m";
    public static final String YELLOW_BOLD = "\033[1;33m";
    public static final String BLUE_BOLD = "\033[1;34m";
    public static final String PURPLE_BOLD = "\033[1;35m";
    public static final String CYAN_BOLD = "\033[1;36m";
    public static final String WHITE_BOLD = "\033[1;37m";

    public static void gameTheme() {
        System.out.println(RED_BOLD +
                " _____                __     __   ________\n" +
                "|     \\ .----..-----.|__|.--|  | |  |  |  |.---.-..----.\n" +
                "|  --  ||   _||  _  ||  ||  _  | |  |  |  ||  _  ||   _|\n" +
                "|_____/ |__|  |_____||__||_____| |________||___._||__|\n" +
                "\n" + RESET);

        System.out.println(PURPLE_BOLD +
                "\t   |\\                     /)\n" +
                "\t /\\_\\\\__               (_//\n" +
                "\t|   `>\\-`     _._       //`)\n" +
                "\t \\ /` \\\\  _.-`:::`-._  //\n" +
                "\t  `    \\|`    :::    `|/\n" +
                "\t        |     :::     |\n" +
                "\t        |.....:::.....|\n" +
                "\t        |:::::::::::::|\n" +
                "\t        |     :::     |\n" +
                "\t        \\     :::     /\n" +
                "\t         \\    :::    /\n" +
                "\t          `-. ::: .-'\n" +
                "\t           //`:::`\\\\\n" +
                "\t          //   '   \\\\\n" +
                "\t         |/         \\\\" +RESET);
    }

    public static void droidStats() {
        System.out.println(YELLOW_BOLD + "Choose a droid:" + RESET + "\n" +
                "1. PROTOSS + " +
                PURPLE_BOLD + "Info: " + GREEN + "hp - 100," + RED + " damage - 25," + RESET +
                WHITE_BOLD + " Special ability: " + CYAN + " Restoring health" + RESET + "\n\n" +

                "2. TERRAN + " +
                PURPLE_BOLD + "Info: " + GREEN + "hp - 100," + RED + " damage - 25," + RESET +
                WHITE_BOLD + " Special ability: " + CYAN + " Deals more damage while having low hp" + RESET + "\n\n" +

                "3. ZERG + " +
                PURPLE_BOLD + "Info: " + GREEN + "hp - 110," + RED + " damage - 15," + RESET +
                WHITE_BOLD + " Special ability: " + CYAN + " Higher damage" + RESET);
    }
}
