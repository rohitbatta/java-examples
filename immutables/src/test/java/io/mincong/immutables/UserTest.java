package io.mincong.immutables;

import java.util.List;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class UserTest {

  @Test
  void itCanCreateUserUsingImmutableBuilder() {
    var user =
        ImmutableUser.builder()
            .name("Tom")
            .emails(List.of("tom@foo.com", "tom@bar.com"))
            .description("Welcome to Immutables")
            .build();

    assertThat(user.name()).isEqualTo("Tom");
    assertThat(user.emails()).containsExactly("tom@foo.com", "tom@bar.com");
    assertThat(user.description()).hasValue("Welcome to Immutables");
  }

  @Test
  void itCanCreateUserUsingBuilder() {
    var user =
        User.builder()
            .name("Tom")
            .emails(List.of("tom@foo.com", "tom@bar.com"))
            .description("Welcome to Immutables")
            .build();

    assertThat(user.name()).isEqualTo("Tom");
    assertThat(user.emails()).containsExactly("tom@foo.com", "tom@bar.com");
    assertThat(user.description()).hasValue("Welcome to Immutables");
  }

  @Test
  void itCanAddItermsUsingBuilder() {
    var user =
        User.builder()
            .name("Tom")
            .addEmails("tom@foo.com", "tom@bar.com")
            .description("Welcome to Immutables")
            .build();

    assertThat(user.name()).isEqualTo("Tom");
    assertThat(user.emails()).containsExactly("tom@foo.com", "tom@bar.com");
    assertThat(user.description()).hasValue("Welcome to Immutables");
  }

  @Test
  void itCanGenerateHashCode() {
    var user1 =
        User.builder()
            .name("Tom")
            .emails(List.of("tom@foo.com", "tom@bar.com"))
            .description("Welcome to Immutables")
            .build();

    var user2 =
        User.builder()
            .name("Tom")
            .emails(List.of("tom@foo.com", "tom@bar.com"))
            .description("Welcome to Immutables")
            .build();

    assertThat(user1.hashCode()).isEqualTo(user2.hashCode());
  }

  @Test
  void itCanGenerateEqual() {
    var user1 =
        User.builder()
            .name("Tom")
            .emails(List.of("tom@foo.com", "tom@bar.com"))
            .description("Welcome to Immutables")
            .build();

    var user2 =
        User.builder()
            .name("Tom")
            .emails(List.of("tom@foo.com", "tom@bar.com"))
            .description("Welcome to Immutables")
            .build();

    assertThat(user1.equals(user2)).isTrue();
    assertThat(user2.equals(user1)).isTrue();
  }

  @Test
  void itCanGenerateToString() {
    var user =
        User.builder()
            .name("Tom")
            .emails(List.of("tom@foo.com", "tom@bar.com"))
            .description("Welcome to Immutables")
            .build();
    assertThat(user.toString())
        .isEqualTo(
            "User{name=Tom, emails=[tom@foo.com, tom@bar.com], description=Welcome to Immutables}");
  }

  @Test
  void itCanHandleOptional() {
    var withDescription =
        User.builder()
            .name("Tom")
            .emails(List.of("tom@foo.com", "tom@bar.com"))
            .description("Welcome to Immutables")
            .build();
    assertThat(withDescription.description()).hasValue("Welcome to Immutables");

    var withoutDescription =
        User.builder().name("Tom").emails(List.of("tom@foo.com", "tom@bar.com")).build();
    assertThat(withoutDescription.description()).isEmpty();
  }

  @Test
  void itCannotAcceptNull() {
    // NullPointerException: name
    // you need to use `java.util.Optional`
    assertThatNullPointerException()
        .isThrownBy(() -> User.builder().name(null).emails(List.of()).build())
        .withMessage("name");
  }

  @Test
  void itCanCreateNewObjectFromExistingViaWith() {
    var user1 =
        User.builder()
            .name("Tom")
            .emails(List.of("tom@foo.com", "tom@bar.com"))
            .description("Welcome to Immutables")
            .build();
    var user2 = user1.withName("Thomas");

    assertThat(user2.name()).isEqualTo("Thomas");
    assertThat(user2.description()).hasValue("Welcome to Immutables");
    assertThat(user2.emails()).containsExactly("tom@foo.com", "tom@bar.com");
  }

  @Test
  void itCanCreateNewObjectFromExistingViaBuilder() {
    var user1 =
        User.builder()
            .name("Tom")
            .emails(List.of("tom@foo.com", "tom@bar.com"))
            .description("Welcome to Immutables")
            .build();
    var user2 = ImmutableUser.builder().from(user1).name("Thomas").build();

    assertThat(user2.name()).isEqualTo("Thomas");
    assertThat(user2.description()).hasValue("Welcome to Immutables");
    assertThat(user2.emails()).containsExactly("tom@foo.com", "tom@bar.com");
  }
}
