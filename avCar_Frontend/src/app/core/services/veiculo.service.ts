import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CrudService } from './crud.service';
import { ConsultaPlacaResponse } from '../models/consulta-placa.model';
import { Veiculo } from '../models/veiculo.model';
import { ProprietarioHistorico } from '../models/proprietario-historico.model';
import { environment } from '../../../environments/environment';

@Injectable({ providedIn: 'root' })
export class VeiculoService extends CrudService<Veiculo> {
  protected apiUrl = `${environment.apiUrl}/veiculos`;

  constructor(http: HttpClient) {
    super(http);
  }

  buscarPorPlaca(placa: string): Observable<Veiculo> {
    return this.http.get<Veiculo>(`${this.apiUrl}/placa/${encodeURIComponent(placa)}`);
  }

  consultarPlacaExterna(placa: string): Observable<ConsultaPlacaResponse> {
    return this.http.get<ConsultaPlacaResponse>(`${this.apiUrl}/consultar-placa/${encodeURIComponent(placa)}`);
  }

  transferirProprietario(idVeiculo: number, idNovoCliente: number): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/${idVeiculo}/transferir-proprietario`, { idNovoCliente });
  }

  getHistorico(idVeiculo: number): Observable<ProprietarioHistorico[]> {
    return this.http.get<ProprietarioHistorico[]>(
      `${environment.apiUrl}/proprietario-historico/veiculo/${idVeiculo}`
    );
  }
}