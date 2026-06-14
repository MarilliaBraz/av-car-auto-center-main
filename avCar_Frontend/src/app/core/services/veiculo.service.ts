import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CrudService } from './crud.service';
import { Veiculo } from '../models/veiculo.model';
import { environment } from '../../../environments/environment';

@Injectable({ providedIn: 'root' })
export class VeiculoService extends CrudService<Veiculo> {
  protected apiUrl = `${environment.apiUrl}/veiculos`;

  constructor(http: HttpClient) {
    super(http);
  }
}