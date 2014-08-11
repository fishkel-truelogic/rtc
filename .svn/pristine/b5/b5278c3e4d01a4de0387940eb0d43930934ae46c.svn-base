package ar.com.finit.service.model.converter;

import java.util.Collection;
import java.util.HashSet;

import ar.com.finit.core.model.Player;
import ar.com.finit.core.model.impl.PlayerImpl;
import ar.com.finit.dto.model.PlayerDTO;

/**
 * @author leo
 */
public class PlayerConverter {

	public Collection<PlayerDTO> toDTO(Collection<Player> players) {
		Collection<PlayerDTO> dtos = new HashSet<PlayerDTO>();
		for (Player player : players) {
			dtos.add(this.toDTO(player));
		}
		return dtos;
	}

	public Collection<Player> toEntity(Collection<PlayerDTO> playersDto) {
		Collection<Player> players = new HashSet<Player>();
		for (PlayerDTO player : playersDto) {
			players.add(this.toEntity(player));
		}
		return players;
	}

	public PlayerDTO toDTO(Player player) {
		if (player == null) {
			return null;
		}
		PlayerDTO dto = new PlayerDTO();
		dto.setId(player.getId());
		dto.setUsername(player.getUsername());
		dto.setPassword(player.getPassword());
		dto.setEmail(player.getEmail());
		dto.setFbId(player.getFbId());
		dto.setEnabled(player.isEnabled());
		dto.setEmailConfirmed(player.isEmailConfirmed());
		return dto;
	}

	public Player toEntity(PlayerDTO dto) {
		if (dto == null) {
			return null;
		}
		PlayerImpl player = new PlayerImpl();
		player.setId(dto.getId());
		player.setUsername(dto.getUsername());
		player.setPassword(dto.getPassword());
		player.setEmail(dto.getEmail());
		player.setFbId(dto.getFbId());
		player.setEnabled(dto.isEnabled());
		player.setEmailConfirmed(dto.isEmailConfirmed());
		return player;
	}
}