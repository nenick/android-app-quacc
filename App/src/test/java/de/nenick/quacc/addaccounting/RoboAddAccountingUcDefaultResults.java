package de.nenick.quacc.addaccounting;

import de.nenick.quacc.TestQuAccApplication;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyListOf;

public class RoboAddAccountingUcDefaultResults {
    public static void apply() {
        given(TestQuAccApplication.coreModuleMocks.recognizeAccountingTypeUc.apply(anyListOf(String.class))).willReturn("");
        given(TestQuAccApplication.coreModuleMocks.getAccountingCategoriesUc.apply()).willReturn(new CharSequence[]{"Beruf", "Essen", "Freizeit", "Miete"});
        given(TestQuAccApplication.coreModuleMocks.getAccountingIntervalsUc.apply()).willReturn(new CharSequence[]{"Einmahlig", "Wöchentlich", "Monatlich", "Alle 3 Monate"});
        given(TestQuAccApplication.coreModuleMocks.getAccountingTypesUc.apply()).willReturn(new CharSequence[]{"Ausgabe", "Einnahme"});
        given(TestQuAccApplication.coreModuleMocks.getAccountsUc.apply()).willReturn(new CharSequence[]{"Konto", "Sparkonto", "Bar"});
    }
}
