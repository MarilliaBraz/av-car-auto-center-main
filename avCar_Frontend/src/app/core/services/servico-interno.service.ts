import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CrudService } from './crud.service';
import { ServicoInterno } from '../models/servico-interno.model';
import { environment } from '../../../environments/environment';

@Injectable({ providedIn: 'root' })
export class ServicoInternoService extends CrudService<ServicoInterno> {
  protected apiUrl = `${environment.apiUrl}/servicos-internos`;

  constructor(http: HttpClient) {
    super(http);
  }
}