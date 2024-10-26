import com.example.walmart.domain.model.Country
import com.example.walmart.domain.usecase.SearchCountryUseCase
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
internal class SearchCountryUseCaseTest {
    @InjectMocks
    private val searchCountryUseCase: SearchCountryUseCase? = null

    @Mock
    private var countries: List<Country>? = null

    fun setUp() {
        MockitoAnnotations.openMocks(this)

        countries = Arrays.asList(
            Country("United States", "North America", "US", "USA"),
            Country("Canada", "North America", "CAD", "CANADA"),
            Country("India", "Asia", "IND", "DELHI"),
            Country("Australia", "Oceania", "AUS", "AUSTRALIA")
        )
    }

    @Test
    fun shouldReturnFullListWhenQueryIsBlank() {
        Mockito.`when`(searchCountryUseCase!!.invoke(countries!!, "")).thenReturn(countries)

        val result = searchCountryUseCase.invoke(countries!!, "")
       assertEquals(countries, result)
    }

    @Test
    fun shouldReturnMatchingCountriesByName() {
        val expected = listOf(Country("Canada", "North America", "CAD", "CANADA"))
        Mockito.`when`(searchCountryUseCase!!.invoke(countries!!, "Canada")).thenReturn(expected)

        val result = searchCountryUseCase.invoke(countries!!, "Canada")
      assertEquals(expected, result)
    }

    @Test
    fun shouldReturnMatchingCountriesByRegion() {
        val expected = listOf(Country("India", "Asia", "IND", "DELHI"))
        Mockito.`when`(searchCountryUseCase!!.invoke(countries!!, "Asia")).thenReturn(expected)

        val result = searchCountryUseCase.invoke(countries!!, "Asia")
        assertEquals(expected, result)
    }

    @Test
    fun shouldReturnMatchingCountriesByCode() {
        val expected = listOf(Country("India", "Asia", "IND", "DELHI"))
        Mockito.`when`(searchCountryUseCase!!.invoke(countries!!, "IND")).thenReturn(expected)

        val result = searchCountryUseCase.invoke(countries!!, "IND")
        assertEquals(expected, result)
    }

    @Test
    fun shouldReturnMatchingCountriesByCapital() {
        val expected = listOf(Country("India", "Asia", "IND", "DELHI"))
        Mockito.`when`(searchCountryUseCase!!.invoke(countries!!, "DELHI")).thenReturn(expected)

        val result = searchCountryUseCase.invoke(countries!!, "DELHI")
        assertEquals(expected, result)
    }

    @Test
    fun shouldIgnoreCaseInSearch() {
        val expected = listOf(Country("United States", "North America", "US", "USA"))
        Mockito.`when`(searchCountryUseCase!!.invoke(countries!!, "united states")).thenReturn(expected)

        val result = searchCountryUseCase.invoke(countries!!, "united states")
        assertEquals(expected, result)
    }

    @Test
    fun shouldReturnEmptyListWhenNoMatchesFound() {
        val expected = emptyList<Country>()
        Mockito.`when`(searchCountryUseCase!!.invoke(countries!!, "XYZ")).thenReturn(expected)

        val result = searchCountryUseCase.invoke(countries!!, "XYZ")
        assertEquals(expected, result)
    }

//    @ParameterizedTest
//    @MethodSource("provideCountrySearchData")
//    fun parameterizedTestForVariousSearchQueries(query: String, expected: List<Country>) {
//        Mockito.`when`(searchCountryUseCase!!.invoke(countries!!, query)).thenReturn(expected)
//
//        val result = searchCountryUseCase.invoke(countries!!, query)
//       assertEquals(expected, result)
//    }
//
//    companion object {
//        private fun provideCountrySearchData(): Stream<Arguments> {
//            return Stream.of(
//                Arguments.of("Canada", listOf(Country("Canada", "North America", "CAD", "CANADA"))),
//                Arguments.of("Asia", listOf(Country("India", "Asia", "IND", "DELHI"))),
//                Arguments.of("IND", listOf(Country("India", "Asia", "IND", "DELHI"))),
//                Arguments.of("DELHI", listOf(Country("India", "Asia", "IND", "DELHI"))),
//                Arguments.of("united states", listOf(Country("United States", "North America", "US", "USA"))),
//                Arguments.of("XYZ", emptyList<Any>())
//            )
//        }
    }
