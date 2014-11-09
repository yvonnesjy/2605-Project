public class transpose {
    public float[][] three_transpose(float[][] input) {
        float[][] output = new float[3][3];
        output[0][0] = input[0][0];
        output[0][1] = input[1][0];
        output[0][2] = input[2][0];
        output[1][0] = input[0][1];
        output[1][1] = input[1][1];
        output[1][2] = input[2][1];
        output[2][0] = input[0][2];
        output[2][1] = input[1][2];
        output[2][2] = input[2][2];
        return output;
    }

    //    public static void main(String[] args){
    //        float[][] myExample3 = {{1,2,3},{4,5,6},{7,8,9}};
    //        float[][] transpose3 = three_transpose(myExample3);
    //        //        float[][] myExample2 = {{1,2},{2,1}};
    //        //        float[][] correcttInverse3 = {{(-5/9),(7/9),(1/9)},{(-2/9),(1/9),(4/9)},{(2/3),(-1/3),(-1/3)}};
    //        //        float[][] outputInverse3 = three_inverse(myExample3);
    //        //        System.out.println(two_determinate(myExample2));
    //        //        System.out.println(1/Math.abs(three_determinate(myExample3)));
    //
    //        for(int a = 0; a<3; a++){
    //            System.out.println();
    //            for(int b = 0; b<3; b++){
    //                System.out.print(myExample3[a][b] + " ");
    //            }
    //        }
    //
    //        for(int a = 0; a<3; a++){
    //            System.out.println();
    //            for(int b = 0; b<3; b++){
    //                System.out.print(transpose3[a][b] + " ");
    //            }
    //        }
    //    }
}
