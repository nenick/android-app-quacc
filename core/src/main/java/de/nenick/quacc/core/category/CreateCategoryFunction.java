package de.nenick.quacc.core.category;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import de.nenick.quacc.core.bookingentry.direction.BookingDirectionOption;
import de.nenick.quacc.core.bookinginterval.BookingIntervalOption;
import de.nenick.quacc.database.category.CategoryRepository;
import de.nenick.quacc.database.category.CategorySpecBySections;
import de.nenick.quacc.database.provider.category.CategoryContentValues;
import de.nenick.quacc.database.provider.category.CategoryCursor;

@EBean
public class CreateCategoryFunction {

    @Bean
    CategoryRepository categoryRepository;

    public void apply(String section, String name, String interval, String direction) {
        createSectionGroupIfNotExist(section);
        createCategory(section, name, interval, direction, 1);
    }

    private void createCategory(String section, String name, String interval, String direction, int level) {
        CategoryContentValues values = new CategoryContentValues()
                .putSection(section)
                .putName(name)
                .putInterval(interval)
                .putDirection(direction)
                .putLevel(level);
        categoryRepository.insert(values);
    }

    private void createSectionGroupIfNotExist(String section) {
        if(!isExistSection(section)) {
            CategoryContentValues values = new CategoryContentValues()
                    .putSection(section)
                    .putName(section)
                    .putInterval(BookingIntervalOption.all.name())
                    .putDirection(BookingDirectionOption.all.name())
                    .putLevel(0);
            categoryRepository.insert(values);
        }
    }

    private boolean isExistSection(String section) {
        CategoryCursor query = categoryRepository.query(new CategorySpecBySections(section));
        boolean exist = query.getCount() > 0;
        query.close();
        return exist;
    }
}
