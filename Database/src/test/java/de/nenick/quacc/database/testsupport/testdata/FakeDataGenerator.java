package de.nenick.quacc.database.testsupport.testdata;

import java.util.Date;

enum FakeDataGenerator {

    StringGenerator(String.class) {
        @Override
        Object generateValue() {
            return "Test" + String.valueOf(sCounter++);
        }
    },
    ShortGenerator(short.class, Short.class) {
        @Override
        Object generateValue() {
            return (short) sCounter++;
        }
    },
    IntegerGenerator(int.class, Integer.class) {
        @Override
        Object generateValue() {
            return (int) sCounter++;
        }
    },
    LongGenerator(long.class, Long.class) {
        @Override
        Object generateValue() {
            return (long) sCounter++;
        }
    },
    BooleanGenerator(boolean.class, Boolean.class) {
        @Override
        Object generateValue() {
            return false;
        }
    },
    FloatGenerator(float.class, Float.class) {
        @Override
        Object generateValue() {
            return (float) sCounter++;
        }
    },
    DoubleGenerator(double.class, Double.class) {
        @Override
        Object generateValue() {
            return sCounter++;
        }
    },
    DateGenerator(Date.class) {
        @Override
        Object generateValue() {
            return new Date((long) sCounter++);
        }
    };

    private static double sCounter = 1.1;
    private Class[] types;

    FakeDataGenerator(Class... types) {
        this.types = types;
    }

    boolean canHandle(Class searched) {
        for (Class type : types) {
            if (type.equals(searched)) {
                return true;
            }
        }
        return false;
    }

    abstract Object generateValue();
}