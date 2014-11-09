
public class inverse {
    public float two_determinate(float[][] input){
        return ((input[1][1]*input[0][0])-(input[0][1]*input[1][0]));
    }

    public float three_determinate(float[][] input){
        float value1 = ((input[0][0]*input[1][1]*input[2][2])+(input[0][1]*input[1][2]*input[2][0])+(input[0][2]*input[1][0]*input[2][1]));
        float value2 = ((-(input[0][2]*input[1][1]*input[2][0]))+(-(input[0][0]*input[1][2]*input[2][1]))+(-(input[0][1]*input[1][0]*input[2][2])));
        return value1+value2;
    }

    public float[][] two_inverse(float[][] input){
        float[][] newInverse = new float[2][2];
        float invserDeter = 1/(Math.abs(two_determinate(input)));

        newInverse[0][0] = invserDeter*input[1][1];
        newInverse[0][1] = invserDeter*-input[1][0];
        newInverse[1][0] = invserDeter*-input[0][1];
        newInverse[1][1] = invserDeter*input[0][0];
        return newInverse;
    }

    public float[][] three_inverse(float[][] input){
        float[][] newInverse = new float[3][3];
        float invserDeter = 1/(Math.abs(three_determinate(input)));

        float[][] topLeft = {{input[1][1],input[1][2]},{input[2][1],input[2][2]}};
        float[][] middleLeft = {{input[1][2],input[1][0]},{input[2][2],input[2][0]}};
        float[][] bottomLeft = {{input[1][0],input[1][1]},{input[2][0],input[2][1]}};

        float[][] topMid = {{input[0][2],input[0][1]},{input[2][2],input[2][1]}};
        float[][] middleMid = {{input[0][0],input[0][2]},{input[2][0],input[2][2]}};
        float[][] bottomMid = {{input[0][1],input[0][0]},{input[2][1],input[2][0]}};

        float[][] topRight = {{input[0][1],input[0][2]},{input[1][1],input[1][2]}};
        float[][] middleRight = {{input[0][2],input[0][0]},{input[1][2],input[1][0]}};
        float[][] bottomRight = {{input[0][0],input[0][1]},{input[1][0],input[1][1]}};

        newInverse[0][0] = invserDeter*two_determinate(topLeft);
        newInverse[0][1] = invserDeter*two_determinate(topMid);
        newInverse[0][2] = invserDeter*two_determinate(topRight);
        newInverse[1][0] = invserDeter*two_determinate(middleLeft);
        newInverse[1][1] = invserDeter*two_determinate(middleMid);
        newInverse[1][2] = invserDeter*two_determinate(middleRight);
        newInverse[2][0] = invserDeter*two_determinate(bottomLeft);
        newInverse[2][1] = invserDeter*two_determinate(bottomMid);
        newInverse[2][2] = invserDeter*two_determinate(bottomRight);
        return newInverse;
    }

    //    public static void main(String[] args){
    //        float[][] myExample3 = {{1,2,3},{2,1,2},{0,3,1}};
    //        float[][] myExample2 = {{1,2},{2,1}};
    //        float[][] correcttInverse3 = {{(-5/9),(7/9),(1/9)},{(-2/9),(1/9),(4/9)},{(2/3),(-1/3),(-1/3)}};
    //        float[][] outputInverse3 = three_inverse(myExample3);
    //        System.out.println(two_determinate(myExample2));
    //        System.out.println(1/Math.abs(three_determinate(myExample3)));
    //        for(int a = 0; a<3; a++){
    //            System.out.println();
    //            for(int b = 0; b<3; b++){
    //                System.out.print(outputInverse3[a][b] + " ");
    //            }
    //        }
    //    }
}
