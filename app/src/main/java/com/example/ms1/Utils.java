package com.example.ms1;

public class Utils {


    public static double[][] probabilty (int dist,double[]init,double[][]tab,int time)
    {




        // le nombre des etats = la taill de vecteur (chaque case represnte une etat)
        int NOMBRE_DES_ETATS = init.length;

        double [] temp = stockTable(init);
        double [] temp1 = stockTable(init);
        double [][] result = new double[time][init.length];


        for(int i=0;i<time;i++)
        {

            for(int j=0;j<NOMBRE_DES_ETATS;j++)
            {
                double[] vertical_table = getVecticalTable(tab,j);
                temp[j] = tableMulti(temp1,vertical_table);
                result[i] = stockTable(temp);
            }


            temp1 = stockTable(temp);

        }

        return result;

    }

    private static double[] getVecticalTable (double[][]t,int n)
    {
        double[] result = new double[t.length];

        for(int i=0;i<t.length;i++)
        {
            result[i] = t[i][n];
        }
        return result;
    }


    private static double tableMulti (double[] t1,double[]t2) {

        double result = 0;

        for(int i=0;i<t1.length;i++)
        {
            result = result + ( t1[i] * t2[i] ) ;
            System.out.println(t1[i]+" * "+t2[i]+" + ");
        }
        System.out.println("\n");
        return result;

    }

    private static double[] stockTable (double[]t2)
    {

        double[] t1 = new double[t2.length];
        for(int i=0; i<t1.length;i++)
        {
            t1[i] = t2[i];
        }

        return t1;
    }



}
