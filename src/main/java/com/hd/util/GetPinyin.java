package com.hd.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 中文转汉语拼音
 */
public class GetPinyin {
    /**
     * 获取中文对应的全拼
     * @param chines
     * @return
     * String
     */
    public static String converterToFirstSpell(String chines) {
        String pinyinName = "";
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < nameChar.length; i++) {
            String s = String.valueOf(nameChar[i]);
            if (s.matches("[\\u4e00-\\u9fa5]")) {
                try {
                    String[] mPinyinArray = PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat);
                    pinyinName += mPinyinArray[0];
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinyinName += nameChar[i];
            }
        }
        return pinyinName;
    }

    /**
     * 得到中文首字母
     * @param str
     * @return
     */
    public static String getPinyinHeadChar(String str){
        String convert="";
        for(int j=0;j<str.length();j++){
            char word=str.charAt(j);
            String []pinyinArr=PinyinHelper.toHanyuPinyinStringArray(word);
            if(pinyinArr!=null){
                convert+=pinyinArr[0].charAt(0);
            }else{
                //convert+=word;
            }
        }
        return convert.toLowerCase();
    }

    public static boolean isHanzi(String str){
       boolean flag=false;
        char[] nameChar = str.toCharArray();
        for (int i = 0; i < nameChar.length; i++) {
            String s = String.valueOf(nameChar[i]);
            if (s.matches("[\\u4e00-\\u9fa5]")) {
               flag=true;
               return flag;
            }
        }
        return flag;
    }
    public static void main(String[] args){
        String str="何志华";
        String pinyin=converterToFirstSpell(str);
        System.out.println(pinyin);
        System.out.println(getPinyinHeadChar(str));
        System.out.println(isHanzi(str));
    }
}
