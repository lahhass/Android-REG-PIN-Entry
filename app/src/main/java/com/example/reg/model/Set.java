package com.example.reg.model;

/**
 * Created by Administrator on 2018/2/8.
 */
import java.util.ArrayList;
import java.util.List;


public class Set {

    private List<Integer> S = new ArrayList<Integer>();
    private List<Integer> P = new ArrayList<Integer>();
    private List<Integer> L = new ArrayList<Integer>();
    private List<Integer> R = new ArrayList<Integer>();
    private List<Integer> LP = new ArrayList<Integer>();

    public Set() {
        ///PiLR(createRandom());
    }

    public void setInit() {
        S.clear();
        P.clear();
        L.clear();
        R.clear();
        gamma();
    }





    //Bucket sort
    public void sort (List<Integer> a) {
        int basket[] = new int[50];
        int i = 0;
        for (i = 0; i < 50; i++) {
            basket[i] = 0;
        }
        for (i = 0; i < a.size(); i++) {
            basket[a.get(i)] ++;
        }
        a.clear();
        for (i = 0; i < 50; i++) {
            if (basket[i] == 1) {
                a.add(i);
            }
        }
    }

    //select five from ten items, the rest
    public int[] randomA (int a, int b) {
        int number = 10;
        int[] LR = new int[10];
        List<Integer> arr = new ArrayList<Integer>();
        for (int i = a; i < b; i++)
            arr.add(i);
        for (int j = 0; j < 5; j++) {
            int index = (int) (Math.random() * number);
            LR[j] = (Integer) arr.get(index);
            arr.remove(index);
            number--;
        }
        for (int j = 5; j < 10; j++) {
            LR[j] = arr.get(j-5);
        }
        arr.clear();
        return LR;
    }

    public void addLR (int[] add) {
        for (int i = 0; i < 5; i++) {
            L.add(add[i]);
        }
        for (int i = 5; i < 10; i++) {
            R.add(add[i]);
        }
    }

    //r(A)
    public void gamma () {
        int[] LR0 = new int[10];
        int[] LR1 = new int[10];
        int[] LR2 = new int[10];
        int[] LR3 = new int[10];
        int[] LR4 = new int[10];

        LR0 = randomA (0, 10);
        LR1 = randomA (10, 20);
        LR2 = randomA (20, 30);
        LR3 = randomA (30, 40);
        LR4 = randomA (40, 50);

        addLR (LR0);
        addLR (LR1);
        addLR (LR2);
        addLR (LR3);
        addLR (LR4);
    }

    //r(S)
    public void gammaL () {
        int l0 = 0;
        int l1 = 0;
        int l2 = 0;
        int l3 = 0;
        int l4 = 0;
        int p0 = 0;
        int p1 = 0;
        int p2 = 0;
        int p3 = 0;
        int p4 = 0;
        int rand = 0;
        int number = S.size();
        List<Integer> Sprime = new ArrayList<Integer>();
        for (int i = 0; i < number; i++) {
            Sprime.add(S.get(i));
        }
        P.clear();
        for (int i = 0; i < L.size(); i++) {
            if (0 <= L.get(i) && L.get(i) <= 9) {
                l0 ++;
            } else if (10 <= L.get(i) && L.get(i) <= 19) {
                l1 ++;
            } else if (20 <= L.get(i) && L.get(i) <= 29) {
                l2 ++;
            } else if (30 <= L.get(i) && L.get(i) <= 39) {
                l3 ++;
            } else if (40 <= L.get(i) && L.get(i) <= 49) {
                l4 ++;
            }
        }
        p0 = 5 - l0;
        p1 = 5 - l1;
        p2 = 5 - l2;
        p3 = 5 - l3;
        p4 = 5 - l4;
        while (p0 > 0 || p1 > 0 || p2 > 0 || p3 > 0 || p4 > 0) {
            int index = (int) (Math.random() * number);
            rand = (Integer) Sprime.get(index);

            if (0 <= rand && rand <= 9 && p0 > 0) {
                P.add(rand);
                Sprime.remove(index);
                p0 --;
                number--;
            } else if (10 <= rand && rand <= 19 &&  p1 > 0) {
                P.add(rand);
                Sprime.remove(index);
                p1 --;
                number--;
            } else if (20 <= rand && rand <= 29 &&  p2 > 0) {
                P.add(rand);
                Sprime.remove(index);
                p2 --;
                number--;
            } else if (30 <= rand && rand <= 39 &&  p3 > 0) {
                P.add(rand);
                Sprime.remove(index);
                p3 --;
                number--;
            } else if (40 <= rand && rand <= 49 &&  p4 > 0) {
                P.add(rand);
                Sprime.remove(index);
                p4 --;
                number--;
            }

        }
    }

    public void SUL() {
        for (int i = 0; i < L.size(); i++) {
            S.add(L.get(i));
        }
    }

    public void SUR() {
        for (int i = 0; i < R.size(); i++) {
            S.add(R.get(i));
        }
    }




    public int[] piL() {
        int number = L.size();
        int a = number;
        int[] random = new int[L.size()];
        List<Integer> arr = new ArrayList<Integer>();
        for (int i = 0; i < number; i++)
            arr.add(L.get(i));
        for (int j = 0; j < a; j++) {
            int index = (int) (Math.random() * number);
            random[j] = (Integer) arr.get(index);
            arr.remove(index);
            number--;
        }
        return random;
    }

    public int[] piR() {
        int number = R.size();
        int a = number;
        int[] random = new int[R.size()];
        List<Integer> arr = new ArrayList<Integer>();
        for (int i = 0; i < number; i++)
            arr.add(R.get(i));
        for (int j = 0; j < a; j++) {
            int index = (int) (Math.random() * number);
            random[j] = (Integer) arr.get(index);
            arr.remove(index);
            number--;
        }
        return random;
    }

    //g(Union)
    public void gLR(int[] random) {
        int i;
        int j;
        L.clear();
        R.clear();
        for (i = 0; i < Math.ceil(((double)random.length)/2); i++) {
            L.add(random[i]);
        }
        for (j = i; j < random.length; j++) {
            R.add(random[j]);
        }
    }

    public int[] getL() {
        int[] l = new int[L.size()];
        for (int i = 0; i < L.size(); i++) {
            l[i] = L.get(i);
        }
        return l;
    }

    public int[] getR() {
        int[] r = new int[R.size()];
        for (int i = 0; i < R.size(); i++) {
            r[i] = R.get(i);
        }
        return r;
    }

    public int[] getS() {
        int[] s = new int[S.size()];
        for (int i = 0; i < S.size(); i++) {
            s[i] = S.get(i);
        }
        return s;
    }

    public int[] getP() {
        int[] p = new int[P.size()];
        for (int i = 0; i < P.size(); i++) {
            p[i] = P.get(i);
        }
        return p;
    }

    public int[] getLUP() {
        int[] lp = new int[L.size() + P.size()];
        int a = L.size();
        int b = P.size();
        int i;
        LP.clear();
        for (i = 0; i < a; i++) {
            LP.add(L.get(i));
        }
        for (i = a; i < (a + b); i++) {
            LP.add(P.get(i - a));
        }
        sort(LP);
        for (i = 0; i < LP.size(); i++) {
            lp[i] = LP.get(i);
        }
        return lp;
    }

    public int[] getWmiLP() {
        int[] lp = new int[25];
        int[] W = new int[50];
        int[] wmlp = new int[25];
        int j = 0;
        lp = getLUP();
        for (int i = 0; i < 50; i++) {
            W[i] = 0;
        }
        for (int i = 0; i < 25; i++) {
            W[lp[i]] = 1;
        }
        for (int i = 0; i < 50; i++) {
            if (W[i] == 0) {
                wmlp[j] = i;
                j++;
            }
        }
        return wmlp;
    }


}

