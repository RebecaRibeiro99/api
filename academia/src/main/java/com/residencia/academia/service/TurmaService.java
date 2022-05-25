package com.residencia.academia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.academia.dto.AtividadeDTO;
import com.residencia.academia.dto.InstrutorDTO;
import com.residencia.academia.dto.TurmaDTO;
import com.residencia.academia.entity.Atividade;
import com.residencia.academia.entity.Instrutor;
import com.residencia.academia.entity.Turma;
import com.residencia.academia.repository.TurmaRepository;

@Service
public class TurmaService {
	@Autowired
	TurmaRepository turmaRepository;
	@Autowired
	InstrutorService instrutorService;
	@Autowired
	AtividadeService atividadeService;

	public List<Turma> findAllTurma() {
		return turmaRepository.findAll();
	}

	public Turma findTurmaById(Integer id) {
		return turmaRepository.findById(id).isPresent() ? turmaRepository.findById(id).get() : null;
	}

	public TurmaDTO findByIdTurmaDTO(Integer id) {
		Turma turma = turmaRepository.findById(id).isPresent() ? turmaRepository.findById(id).get() : null;

		TurmaDTO turmaDTO = new TurmaDTO();
		if (null != turma) {
			turmaDTO = converterEntidadeParaDTO(turma);
			return turmaDTO;
		}
		return null;
	}

	public Turma saveTurma(Turma turma) {
		return turmaRepository.save(turma);
	}

	public TurmaDTO saveTurmaDTO(TurmaDTO turmaDTO) {
		Turma turma = conversorDTOParaEntidade(turmaDTO);
		Turma turmaNovo = turmaRepository.save(turma);
		return converterEntidadeParaDTO(turmaNovo);
	}

	public Turma updateTurma(Turma turma) {
		return turmaRepository.save(turma);
	}

	public void deleteTurma(Integer id) {
		turmaRepository.deleteById(id);
	}

	private TurmaDTO converterEntidadeParaDTO(Turma turma) {
		TurmaDTO turmaDTO = new TurmaDTO();
		turmaDTO.setIdTurma(turma.getIdTurma());
		turmaDTO.setHorarioTurma(turma.getHorarioTurma());
		turmaDTO.setDuracaoTurma(turma.getDuracaoTurma());
		turmaDTO.setDataInicio(turma.getDataInicio());
		turmaDTO.setDataFim(turma.getDataFim());
		InstrutorDTO instrutorDTO = instrutorService.findInstrutorDTOById(turma.getInstrutor().getIdInstrutor());
        turmaDTO.setInstrutorDTO(instrutorDTO);
        AtividadeDTO atividadeDTO = atividadeService.findAtividadeDTOById(turma.getAtividade().getIdAtividade());
        turmaDTO.setAtividadeDTO(atividadeDTO);
		
		return turmaDTO;
	}

	private Turma conversorDTOParaEntidade(TurmaDTO turmaDTO) {
		Turma turma = new Turma();
		turma.setIdTurma(turmaDTO.getIdTurma());
		turma.setHorarioTurma(turmaDTO.getHorarioTurma());
		turma.setDuracaoTurma(turmaDTO.getDuracaoTurma());
		turma.setDataInicio(turmaDTO.getDataInicio());
		turma.setDataFim(turmaDTO.getDataFim());
		Instrutor instrutor = instrutorService.findInstrutorById(turmaDTO.getInstrutorDTO().getIdInstrutor());
		turma.setInstrutor(instrutor);
		Atividade atividade = atividadeService.findAtividadeById(turmaDTO.getAtividadeDTO().getIdAtividade());
		turma.setAtividade(atividade);
		return turma;
	}
}
