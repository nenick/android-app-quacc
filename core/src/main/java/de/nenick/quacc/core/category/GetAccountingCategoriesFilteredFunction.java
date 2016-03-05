package de.nenick.quacc.core.category;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import de.nenick.quacc.core.bookinginterval.BookingIntervalOption;
import de.nenick.quacc.core.bookingentry.direction.BookingDirectionOption;
import de.nenick.quacc.database.category.CategoryRepository;
import de.nenick.quacc.database.category.CategorySpecFiltered;
import de.nenick.quacc.database.provider.category.CategoryCursor;

@EBean
public class GetAccountingCategoriesFilteredFunction {

    @Bean
    CategoryRepository categoryRepository;

    public CategoryCursor apply(String interval, String direction) {
        String[] intervals = {interval, BookingIntervalOption.all.name()};
        String[] directions = {direction, BookingDirectionOption.all.name()};
        return categoryRepository.query(new CategorySpecFiltered(intervals, directions));
    }
}
