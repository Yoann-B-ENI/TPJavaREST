package fr.eni.vioyo.RestLudotheque.dal;

import fr.eni.vioyo.RestLudotheque.bo.GameCopy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameCopyRepository extends JpaRepository<GameCopy, Integer> {
}
