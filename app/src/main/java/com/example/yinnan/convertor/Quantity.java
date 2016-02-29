package com.example.yinnan.convertor;

import java.text.DecimalFormat;

/**
 * Created by Yinnan on 12/12/2015.
 */
public class Quantity {

    //each quantity will have a value and a unit object;
    final double value;
    final Unit unit;

    //unit object will contain a baseUnit tsp object and a value in baseUnit;

    public static enum Unit {
        //constructors of different units
        //name(attributes);
        tsp(1.0d), tbs(0.3333d), cup(0.0208d), oz(0.1666d),
        pint(0.0104d), quart(0.0052d), gallon(0.0013), pound(0.0125d),
        ml(4.9289d), liter(0.0049d), mg(5687.5d), kg(0.0057d);

        final static Unit baseUnit = tsp;
        final double byBaseUnit;

        //in private constructor just need to initialize the baseUnit
        private Unit(double inTsp) {
            this.byBaseUnit = inTsp;
        }

        //convert to baseUnit
        public double toBaseUnit(double value) {
            return value/byBaseUnit;
        }

        //convert from baseUnit
        public double fromBaseUnit(double value) {
            return value*byBaseUnit;
        }
    }

    //in quantity constructor, need to initialize value and unit
    public Quantity(double value, Unit unit) {
        super();
        this.value = value;
        this.unit = unit;
    }

    //use the original unit to calculate the val in baseUnit
    //convertFromUnit is the current unit of this quantity
    //use toBaseUnit to get the value in baseUnit
    //convertToUnit is the unit we want
    //use fromBaseUnit to get the value in new unit
    //return new quantity object with calculated value and convertToUnit
    public Quantity to(Unit convertToUnit) {
        Unit convertFromUnit = this.unit;
        double value = this.value;
        return new Quantity (convertToUnit.fromBaseUnit(convertFromUnit.toBaseUnit(value)),
                convertToUnit);
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.000");
        return df.format(value) + " " + unit.name();
    }
}
