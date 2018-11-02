package com.liquidice.acidrain.utilities;

public class SpriteUtil {

    /**
     * Utility method to return the X/Y coordinate representing the middle of the object's total height/width
     * @param fullSize  The object's total height OR width
     * @return  The midway point of the Objec'ts total height OR width
     */
    public static float middleOf(float fullSize) {
        return fullSize / 2;
    }

}
