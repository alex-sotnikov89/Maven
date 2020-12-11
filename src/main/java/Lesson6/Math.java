package Lesson6;


public class Math {
    public static void main(String[] args) {

//        Math math = new Math();
//        int[] array = new int[]{1,2,3,4,5,6,7};
//        System.out.println(Arrays.toString(math.returnArrayAfterLastValue(array, 5)));

        int[] array = new int[]{1,2,3,4,5,6,7};
        System.out.println(isArrayContainValues(array,8,7));
    }

    public static int[] returnArrayAfterLastValue(int[] array, int value) {
        if (array == null || array.length == 0) {
            throw new RuntimeException("Array is null or empty!");
        }
        int index = -1;
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] == value) {
                index = i+1;
            }
        }
        if (index == -1) {
            throw new RuntimeException("Array does not contain value: " + value);
        }
        int newArrayLength = array.length - index;
        int[] newArray = new int[newArrayLength];
        System.arraycopy(array, index, newArray, 0, newArrayLength);
        return newArray;
    }

    public static boolean isArrayContainValues(int[] array, int...a){
        if (array == null || array.length == 0) {
            return false;
        }
        for (int i = 0; i <a.length ; i++) {
            for (int j = 0; j < array.length; j++) {
                if (a[i]==array[j]){
                    return true;
                }
            }
        }
        return false;
    }
}
