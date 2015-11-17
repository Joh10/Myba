package Server;

/**
 * Created by Jo on 29/04/15.
 */

public class Adresse
{
    private int codePostal;
    private String boite;
    private String street;
    private int number;
    private String country;
    private String city;

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    public Adresse(String street, int number, String boite, int codePostal, String city, String country)
    {
        this.codePostal = codePostal;
        this.boite = boite;
        this.street = street;
        this.number = number;
        this.country = country;
        this.city = city;
    }

    public Adresse(String street, int number, int codePostal, String city, String country)
    {
        this.codePostal = codePostal;
        this.street = street;
        this.number = number;
        this.country = country;
        this.city = city;
    }

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    public int getCodePostal()
    {
        return codePostal;
    }

    public void setCodePostal(int codePostal)
    {
        this.codePostal = codePostal;
    }

    public String getBoite()
    {
        return boite;
    }

    public void setBoite(String boite)
    {
        this.boite = boite;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public int getNumber()
    {
        return number;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    @Override
    public String toString()
    {
        return boite + " " + number + " " + street + " " + city + " " + codePostal + " " + country;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Adresse)) return false;

        Adresse adresse = (Adresse) o;

        if (codePostal != adresse.codePostal) return false;
        if (number != adresse.number) return false;
        if (boite != null ? !boite.equals(adresse.boite) : adresse.boite != null) return false;
        if (street != null ? !street.equals(adresse.street) : adresse.street != null) return false;
        if (country != null ? !country.equals(adresse.country) : adresse.country != null) return false;
        return !(city != null ? !city.equals(adresse.city) : adresse.city != null);

    }

    @Override
    public int hashCode()
    {
        int result = codePostal;
        result = 31 * result + (boite != null ? boite.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + number;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }
}
