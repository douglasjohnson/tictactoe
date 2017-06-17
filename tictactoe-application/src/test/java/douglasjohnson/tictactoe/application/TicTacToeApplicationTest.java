package douglasjohnson.tictactoe.application;

import org.junit.Test;
import org.springframework.boot.builder.SpringApplicationBuilder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TicTacToeApplicationTest {

    @Test
    public void shouldConfigureSpringApplicationBuilder() {
        TicTacToeApplication application = new TicTacToeApplication();
        SpringApplicationBuilder springApplicationBuilder = mock(SpringApplicationBuilder.class);
        application.configure(springApplicationBuilder);
        verify(springApplicationBuilder).sources(TicTacToeApplication.class);
    }
}
