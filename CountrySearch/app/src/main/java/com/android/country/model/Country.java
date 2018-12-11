package com.android.country.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;
import com.google.gson.annotations.SerializedName;

@Entity
public class Country {

    @PrimaryKey
    @NonNull
    @SerializedName("name")
    private String name;

    @SerializedName("topLevelDomain")
    @TypeConverters(StringArrayConverter.class)
    private String[] domains;

    @SerializedName("alpha2Code")
    private String alpha2Code;

    @SerializedName("alpha3Code")
    private String alpha3Code;

    @SerializedName("callingCodes")
    @TypeConverters(StringArrayConverter.class)
    private String[] callingCodes;

    @SerializedName("capital")
    private String capital;

    @SerializedName("altSpellings")
    @TypeConverters(StringArrayConverter.class)
    private String[] altSpellings;

    @SerializedName("region")
    private String region;

    @SerializedName("subregion")
    private String subRegion;

    @SerializedName("population")
    private long population;

    @SerializedName("latlng")
    @TypeConverters(StringArrayConverter.class)
    private String[] latlag;

    @SerializedName("demonym")
    private String demonym;

    @SerializedName("area")
    private String area;

    @SerializedName("gini")
    private String gini;

    @SerializedName("timezones")
    @TypeConverters(StringArrayConverter.class)
    private String[] timezones;

    @SerializedName("borders")
    @TypeConverters(StringArrayConverter.class)
    private String[] borders;

    @SerializedName("translations")
    @TypeConverters(TranslationConverter.class)
    private Translation translations;

    @SerializedName("nativeName")
    private String nativeName;

    @SerializedName("numericCode")
    private String numericCode;

    @SerializedName("currencies")
    @TypeConverters(CurrencyConverter.class)
    private Currency[] currencies;

    @SerializedName("languages")
    @TypeConverters(LanguageConverter.class)
    private Launguage[] languages;

    @SerializedName("flag")
    private String flag;

    @SerializedName("regionalBlocs")
    @TypeConverters(RegionalBlocConverter.class)
    private RegionalBlocs[] regionalBlocs;

    @SerializedName("cioc")
    private String cioc;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getDomains() {
        return domains;
    }

    public void setDomains(String[] domains) {
        this.domains = domains;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    public String[] getCallingCodes() {
        return callingCodes;
    }

    public void setCallingCodes(String[] callingCodes) {
        this.callingCodes = callingCodes;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String[] getAltSpellings() {
        return altSpellings;
    }

    public void setAltSpellings(String[] altSpellings) {
        this.altSpellings = altSpellings;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubRegion() {
        return subRegion;
    }

    public void setSubRegion(String subRegion) {
        this.subRegion = subRegion;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public String[] getLatlag() {
        return latlag;
    }

    public void setLatlag(String[] latlag) {
        this.latlag = latlag;
    }

    public String getDemonym() {
        return demonym;
    }

    public void setDemonym(String demonym) {
        this.demonym = demonym;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getGini() {
        return gini;
    }

    public void setGini(String gini) {
        this.gini = gini;
    }

    public String[] getTimezones() {
        return timezones;
    }

    public void setTimezones(String[] timezones) {
        this.timezones = timezones;
    }

    public String[] getBorders() {
        return borders;
    }

    public void setBorders(String[] borders) {
        this.borders = borders;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public String getNumericCode() {
        return numericCode;
    }

    public void setNumericCode(String numericCode) {
        this.numericCode = numericCode;
    }

    public Currency[] getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Currency[] currencies) {
        this.currencies = currencies;
    }

    public Launguage[] getLanguages() {
        return languages;
    }

    public void setLanguages(Launguage[] languages) {
        this.languages = languages;
    }

    public Translation getTranslations() {
        return translations;
    }

    public void setTranslations(Translation translations) {
        this.translations = translations;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public RegionalBlocs[] getRegionalBlocs() {
        return regionalBlocs;
    }

    public void setRegionalBlocs(RegionalBlocs[] regionalBlocs) {
        this.regionalBlocs = regionalBlocs;
    }

    public String getCioc() {
        return cioc;
    }

    public void setCioc(String cioc) {
        this.cioc = cioc;
    }




}
