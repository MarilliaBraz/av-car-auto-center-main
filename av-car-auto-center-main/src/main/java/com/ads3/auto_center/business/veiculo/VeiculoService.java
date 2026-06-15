package com.ads3.auto_center.business.veiculo;

import com.ads3.auto_center.business.cliente.ClienteModel;
import com.ads3.auto_center.business.proprietarioHistorico.ProprietarioHistoricoService;
import com.ads3.auto_center.core.exceptions.BusinessException;
import com.ads3.auto_center.core.services.GenericService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VeiculoService extends GenericService<VeiculoModel, IVeiculoRepository, VeiculoValidation> {

    @Autowired
    private ProprietarioHistoricoService proprietarioHistoricoService;

    @PersistenceContext
    private EntityManager em;

    public VeiculoModel buscarPorPlaca(String placa) {
        return repository.findByPlaca(placa)
                .orElseThrow(() -> new BusinessException("Veículo não encontrado para a placa informada.", HttpStatus.NOT_FOUND));
    }

    @Override
    protected void afterInsert(VeiculoModel saved, VeiculoModel original) {
        if (saved.getCliente() != null) {
            proprietarioHistoricoService.registrarProprietario(saved, saved.getCliente());
        }
    }

    @Override
    protected void beforeUpdate(VeiculoModel entity) {
        // Alteração de proprietário ocorre apenas pelo endpoint de transferência
        repository.findByIdAndAtivoTrue(entity.getId()).ifPresent(original -> {
            entity.setCliente(original.getCliente());
        });
    }

    @Transactional
    public void transferirProprietario(Long idVeiculo, Long idNovoCliente) {
        VeiculoModel veiculo = findByIdActive(idVeiculo);
        ClienteModel novoCliente = em.getReference(ClienteModel.class, idNovoCliente);

        proprietarioHistoricoService.registrarProprietario(veiculo, novoCliente);

        veiculo.setCliente(novoCliente);
        repository.save(veiculo);
    }
}