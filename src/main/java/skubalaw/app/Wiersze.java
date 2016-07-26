package skubalaw.app;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

/**
 * Created by adamczykp on 18.07.2016.
 */
public interface Wiersze {


    <T> T getName();

    List<String> list = new ArrayList<>();


    enum Wiersz1 implements Wiersze {
        MAIN1("MAIN1") {
            Wiersz20 getUpg1() {
                list.add("MAIN1");
                list.add(Wiersz20.UPGR1.getName());
                return Wiersz20.UPGR1;
            }

        }, MAIN2("MAIN2") {
            Wiersz20 getUpg3() {
                list.add("MAIN2");
                list.add(Wiersz20.UPGR3.getName());
                return Wiersz20.UPGR3;
            }
        },
        none("clear") {
            Wiersz20 getUpg2() {
                list.add(Wiersz20.UPGR2.getName());
                return Wiersz20.UPGR2;
            }

        };

        String nazwa;

        Wiersz1(String main2) {
            this.nazwa = main2;
        }

        Wiersz20 getUpg1() {
            throw new EmptyStackException();
        }

        Wiersz20 getUpg2() {
            throw new EmptyStackException();

        }

        Wiersz20 getUpg3() {
            throw new EmptyStackException();

        }

        @Override
        public <T> T getName() {
           // System.out.println(nazwa);
            return (T) nazwa;
        }
    }


    enum Wiersz20 implements Wiersze {
        UPGR1("UPGR1") {
            @Override
            Wiersz2 dowGr1() {
                list.add(Wiersz2.DOWGR1.getName());

                return Wiersz2.DOWGR1;
            }

            @Override
            Wiersz2 dowGr2() {
                list.add(Wiersz2.DOWGR2.getName());

                return Wiersz2.DOWGR2;
            }
        }, UPGR2("UPGR2") {
            @Override
            Wiersz2 dowGr3() {
                list.add(Wiersz2.DOWGR3.getName());

                return Wiersz2.DOWGR3;
            }

            @Override
            Wiersz2 dowGr4() {
                list.add(Wiersz2.DOWGR4.getName());
                return Wiersz2.DOWGR4;
            }
        }, UPGR3("UPGR3") {
            @Override
            Wiersz2 dowGr5() {
                list.add(Wiersz2.DOWGR5.getName());

                return Wiersz2.DOWGR5;
            }

            @Override
            Naglowki naglowek10() {
                list.add(Naglowki.COL10.getName());

                return Naglowki.COL10;
            }

            @Override
            Naglowki naglowek11() {
                list.add(Naglowki.COL11.getName());

                return Naglowki.COL11;
            }
        };

//        throw new EmptyStackException();

        Wiersz2 dowGr1() {
            throw new EmptyStackException();
        }

        Wiersz2 dowGr2() {
            throw new EmptyStackException();
        }

        Wiersz2 dowGr3() {
            throw new EmptyStackException();
        }

        Wiersz2 dowGr4() {
            throw new EmptyStackException();
        }

        Wiersz2 dowGr5() {
            throw new EmptyStackException();
        }

        Naglowki naglowek10() {
            throw new EmptyStackException();
        }

        Naglowki naglowek11() {
            throw new EmptyStackException();
        }

        String name;

        Wiersz20(String upgr1) {
            this.name = upgr1;
        }

        @Override
        public <T> T getName() {
           // System.out.println(name);
            return (T) name;
        }
    }


    enum Wiersz2 implements Wiersze {
        DOWGR1("DOWGR1") {
            @Override
            Naglowki naglowek1() {
                list.add(Naglowki.COL1.getName());

                return Naglowki.COL1;
            }

            @Override
            Naglowki naglowek2() {
                list.add(Naglowki.COL2.getName());

                return Naglowki.COL2;
            }

            @Override
            Naglowki naglowek3() {
                list.add(Naglowki.COL3.getName());

                return Naglowki.COL3;
            }
        }, DOWGR2("DOWGR2") {
            @Override
            Naglowki naglowek4() {
                list.add(Naglowki.COL4.getName());

                return Naglowki.COL4;
            }
        }, DOWGR3("DOWGR3") {
            @Override
            Naglowki naglowek5() {
                list.add(Naglowki.COL5.getName());

                return Naglowki.COL5;
            }

            @Override
            Naglowki naglowek6() {
                list.add(Naglowki.COL6.getName());

                return Naglowki.COL6;
            }
        }, DOWGR4("DOWGR4") {
            @Override
            Naglowki naglowek7() {
                list.add(Naglowki.COL7.getName());

                return Naglowki.COL7;

            }
        }, DOWGR5("DOWGR5") {
            @Override
            Naglowki naglowek8() {
                list.add(Naglowki.COL8.getName());

                return Naglowki.COL8;
            }

            @Override
            Naglowki naglowek9() {
                list.add(Naglowki.COL9.getName());

                return Naglowki.COL9;

            }
        };

        String name;

        Wiersz2(String upgr2) {
            this.name = upgr2;
        }

        Naglowki naglowek1() {
            throw new EmptyStackException();
        }

        Naglowki naglowek2() {
            throw new EmptyStackException();
        }

        Naglowki naglowek3() {
            throw new EmptyStackException();
        }

        Naglowki naglowek4() {
            throw new EmptyStackException();
        }

        Naglowki naglowek5() {
            throw new EmptyStackException();
        }

        Naglowki naglowek6() {
            throw new EmptyStackException();
        }

        Naglowki naglowek7() {
            throw new EmptyStackException();
        }

        Naglowki naglowek8() {
            throw new EmptyStackException();
        }

        Naglowki naglowek9() {
            throw new EmptyStackException();
        }

        Naglowki naglowek10() {
            throw new EmptyStackException();
        }

        Naglowki naglowek11() {
            throw new EmptyStackException();
        }


        @Override
        public <T> T getName() {
           // System.out.println(name);
            return (T) name;
        }
    }


    enum Naglowki implements Wiersze {
        COL1("COL1"), COL2("COL2"), COL3("COL3"), COL4("COL4"), COL5("COL5"), COL6("COL8"), COL7("COL7"), COL8("COL8"), COL9("COL9"), COL10("COL10"), COL11("COL11");

        String name;

        Naglowki(String n4) {
            this.name = n4;
        }

        @Override
        public <T> T getName() {
            //System.out.println(name);
            return (T) name;
        }
    }

}
