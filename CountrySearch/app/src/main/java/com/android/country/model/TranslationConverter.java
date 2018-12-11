package com.android.country.model;

import android.arch.persistence.room.TypeConverter;
import android.util.Log;


public class TranslationConverter {

    @TypeConverter
    public String toStringTrans(Translation translation){
        return "de=" + translation.getDe() +
                ",es=" + translation.getEs() +
                ",fr=" + translation.getFr() +
                ",ja=" + translation.getJa() +
                ",it=" + translation.getIt() +
                ",br=" + translation.getBr() +
                ",pt=" + translation.getPt() +
                ",nl=" + translation.getNl() +
                ",hr=" + translation.getHr() +
                ",fa=" + translation.getFa() ;

    }

    @TypeConverter
    public Translation stringToTrnalation(String str) {
        String[] trans = str.split(",");
        Log.d("stringToTrnalation",str+" length "+trans.length);
        Translation translation = new Translation();
        translation.setDe(trans[0]);
        translation.setEs(trans[1]);
        translation.setFr(trans[2]);
        translation.setJa(trans[3]);
        translation.setIt(trans[4]);
        translation.setBr(trans[5]);
        translation.setPt(trans[6]);
        translation.setNl(trans[7]);
        translation.setHr(trans[8]);
        translation.setFa(trans[9]);
        return translation;
    }


}
