package _99_extra._00_league_of_amazing_astronauts;

import _99_extra._00_league_of_amazing_astronauts.LeagueOfAmazingAstronauts;
import _99_extra._00_league_of_amazing_astronauts.models.Astronaut;
import _99_extra._00_league_of_amazing_astronauts.models.Rocketship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/*

When writing the tests, mock both the Rocketship and Astronaut for the sake of practice.
 */
class LeagueOfAmazingAstronautsTest {

    LeagueOfAmazingAstronauts underTest = new LeagueOfAmazingAstronauts();

    @Mock
    Astronaut yuriGagarin;

    @Mock
    Rocketship vostok;

    @BeforeEach
    void setUp() {
MockitoAnnotations.openMocks(this);
    }

    @Test
    void itShouldPrepareAstronaut() {
        //given
        underTest.setRocketship(vostok);
        //when
underTest.prepareAstronaut(yuriGagarin);
        //then
        verify(yuriGagarin, times(1)).train();
        verify(vostok, times(1)).loadOccupant(yuriGagarin);
    }

    @Test
    void itShouldLaunchRocket() {
        //given
        underTest.setRocketship(vostok);
        when(vostok.isLoaded()).thenReturn(true);
        //when
underTest.launchRocket("Mars");
        //then
verify(vostok, times(1)).launch();
    }


    @Test
    void itShouldThrowWhenDestinationIsUnknown() {
        //given

        //when
        //then
    }

    @Test
    void itShouldThrowNotLoaded() {
        //given
        underTest.setRocketship(vostok);
when(vostok.isLoaded()).thenReturn(false);
        //when

        //then
        Throwable exceptionThrown = assertThrows(Exception.class, () -> underTest.launchRocket("Cuba"));
        assertEquals(exceptionThrown.getMessage(), "This astronaut is not trained");
    }
}