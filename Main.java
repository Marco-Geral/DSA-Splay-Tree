public class Main {
    
    public static int PRINT_MODE = 1;
    public static int SUITES_RUN = 0;
    public static int SUITES_PASSED = 0;
    public static int TESTS_RUN = 0;
    public static int TESTS_PASSED = 0;
    
    public static void main(String[] args) {
        
        System.out.println("-----------------------TESTING CONSTRUCTOR-----------------------");   
        SplayTree st0 = new SplayTree();
        System.out.println(st0.toString() + "\n");   
        SplayTree st1 = new SplayTree("{[u10:50%]{}{}}");
        System.out.println(st1.toString() + "\n");
        SplayTree st2 = new SplayTree("{[u50:85%]{[u60:20%]{[u90:90%]{}{}}{}}{}}");
        System.out.println(st2.toString() + "\n");

        System.out.println("-----------------------TESTING ACCESSING (INSERT)-----------------------");   
        System.out.println("\nTesting ST1");   
        st1.access(15, 80);
        st1.access(5, 60);
        st1.access(3, 90);
        st1.access(2, 40);
        System.out.println(st1.toString() + "\n");
        System.out.println("\nTesting ST2"); 
        st2.access(40, 50);
        st2.access(60, 60);
        st2.access(30, 75);
        st2.access(70, 62);
        System.out.println(st2.toString() + "\n");
        st2.access(70,63);
        System.out.println(st2.toString() + "\n"); //expecting u70 to get splayed to top and mark change to 63
        st2.access(1000);
        System.out.println(st2.toString() + "\n");

        System.out.println("-----------------------TESTING ACCESSING (ACCESSING)-----------------------"); 
        System.out.println("\nTesting ST1");
        System.out.println(st1.access(5) + "\n");
        System.out.println("IS this one\n" + st1.toString() + "\n");

        System.out.println("\nTesting ST2");
        System.out.println(st2.access(50) + "\n"); 
        System.out.println(st2.toString() + "\n"); //Expecting u50 to get splayed to the root without changing mark

        System.out.println("-----------------------TESTING REMOVE-----------------------"); 
        System.out.println("\nTesting ST1");
        st1.remove(5);
        System.out.println(st1.toString() + "\n");
        st1.remove(15);
        System.out.println(st1.toString() + "\n");
        System.out.println("\nTesting ST2");
        st2.remove(50);
        System.out.println(st2.toString() + "\n");
        st2.remove(100);
        System.out.println(st2.toString() + "\n"); // remove something that is not there

        System.out.println("-----------------------TESTING SORT_BY_STUDENT_NUMBER-----------------------"); 
        System.out.println(st1.sortByStudentNumber() + "\n");


        System.out.println("-----------------------TESTING SORT_BY_MARK-----------------------");
        System.out.println(st1.sortByMark() + "\n");
        

        SplayTree st3 = new SplayTree("{[u10:null%]{}{}}");
        System.out.println(st3.toString() + "\n");
        SplayTree st4 = new SplayTree("{[u10:50%]{[u20:null%]{}{}}{[u60:null%]{}{}}}");
        System.out.println(st4.toString() + "\n");
        
       
        SplayTree st6 = new SplayTree("{[u50:20%]{}{}}");
        st6.access(30, 20);
        System.out.println(st6.toString());
        st6.access(40, 20);
        System.out.println(st6.toString());
        st6.access(35, 20);
        System.out.println(st6.toString());
        st6.access(45, 20);
        System.out.println(st6.toString());
        st6.access(100, 20);
        System.out.println(st6.toString());

        System.out.println("---------------------------EEEEEEE--------------------\n");
        SplayTree st8 = new SplayTree("{[u65:null%]{}{}}");
        st8.access(55);
        System.out.println(st8.toString());
        st8.access(45);
        System.out.println(st8.toString());
        st8.access(35);
        System.out.println(st8.toString());
        st8.access(60);
        System.out.println(st8.toString());
        st8.access(40);
        System.out.println(st8.toString());
        st8.access(50);
        System.out.println(st8.toString());
        st8.access(100);
        System.out.println(st8.toString());
        st8.access(40, 50);
        System.out.println(st8.toString());

        System.out.println("---------------------------EEEEEEE--------------------\n");
        

        System.out.println("--------------------TESTING ACCESSING (ACCESSING AGAIN)-----------------------");
        SplayTree st7 = new SplayTree("{[u50:20%]{[u40:20%]{[u35:20%]{}{}}{[u45:20%]{}{}}}{[u60:20%]{[u55:20%]{}{}}{[u65:20%]{}{}}}}");
        System.out.println(st7.toString());
        st7.access(100, 30);
        System.out.println(st7.toString());
        st7.access(50, 30);
        System.out.println(st7.toString());
        
        
        System.out.println("--------------------TESTING REMOVE (AGAIN)-----------------------");
        SplayTree st10 = new SplayTree("{[u50:20%]{[u40:20%]{[u35:20%]{}{}}{[u45:20%]{}{}}}{[u60:20%]{[u55:20%]{}{}}{[u65:20%]{}{}}}}");
        System.out.println(st10.toString());
        st10.remove(45);
        System.out.println(st10.toString());
        st10.remove(65);
        System.out.println(st10.toString());

    }

        public static void startSuite(String name) {
        switch (PRINT_MODE) {
            case 1:
                SUITES_RUN++;
                System.out.println("===================\nStarting: " + name + "\n===================");
                break;
        }
    }

    public static void endSuite() {
        switch (PRINT_MODE) {
            case 1:
                if (TESTS_RUN == TESTS_PASSED) {
                    SUITES_PASSED++;
                    System.out.println("All Tests Passed " + "\n===================");
                } else {
                    System.out.println("Tests Failed: " + (TESTS_RUN - TESTS_PASSED)
                            + "\n===================" );
                }
                TESTS_RUN = 0;
                TESTS_PASSED = 0;
                break;
        }
    }

    public static <T> void assertEquals(T actual, T expected) {
        switch (PRINT_MODE) {
            case 1:
                TESTS_RUN++;
                if (actual.equals(expected)) {
                    TESTS_PASSED++;
                    System.out.println("Test " + TESTS_RUN + " Passed ");
                } else {
                    System.out.println("Test " + TESTS_RUN + " Failed: Expected " + expected + "\nbut got:\n"
                            + "\n" + actual );
                }
                break;
            case 2:
                System.out.println(actual);
                break;
        }
    }

    public static void assertEquals(String actual, String expected) {
        switch (PRINT_MODE) {
            case 1:
                TESTS_RUN++;
                if (actual.equals(expected)) {
                    TESTS_PASSED++;
                    System.out.println("Test " + TESTS_RUN + " Passed ");
                } else {
                    System.out.println("Test " + TESTS_RUN + " Failed: Expected ");
                    boolean wrong = false;
                    for (int i = 0; i < expected.length(); i++) {
                        if (i < actual.length() && actual.charAt(i) == expected.charAt(i)) {
                            if (wrong) {
                                //System.out.print(ANSI_RESET);
                                wrong = false;
                            }
                        } else if (!wrong) {
                            //System.out.print(ANSI_RED);
                            wrong = true;
                        }
                        System.out.print(expected.charAt(i));
                    }
                    System.out.print("\nbut got\n");
                    wrong = false;
                    for (int i = 0; i < actual.length(); i++) {
                        if (i < expected.length() && actual.charAt(i) == expected.charAt(i)) {
                            if (wrong) {
                                //System.out.print(ANSI_RESET);
                                wrong = false;
                            }
                        } else if (!wrong) {
                            //System.out.print(ANSI_RED);
                            wrong = true;
                        }
                        System.out.print(actual.charAt(i));
                    }
                    //System.err.println(ANSI_RESET);
                }
                break;
            case 2:
                System.out.println(actual);
                break;
        }
    }

    public static void endAll() {
        switch (PRINT_MODE) {
            case 1:
                if (SUITES_RUN == SUITES_PASSED) {
                    System.out.println(
                             "\n\n===================\n" + "All Suites Passed " + SUITES_PASSED + "/"
                                    + SUITES_RUN + "\n===================");
                } else {
                    System.out.println("===================\n" + "Some Suites Failed: " + SUITES_PASSED + "/"
                            + SUITES_RUN + "\n===================");
                }
                break;
        }
    }
}