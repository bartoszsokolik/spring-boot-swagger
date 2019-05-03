package pl.solutions.software.sokolik.bartosz.movie.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface MovieRepository extends JpaRepository<Movie, Long> {

    Optional<Movie> findByTitle(String title);

}
