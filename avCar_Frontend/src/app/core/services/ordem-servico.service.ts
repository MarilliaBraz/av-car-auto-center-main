import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CrudService } from './crud.service';
import { OrdemServico } from '../models/ordem-servico.model';
import { environment } from '../../../environments/environment';

@Injectable({ providedIn: 'root' })
export class OrdemServicoService extends CrudService<OrdemServico> {
  protected apiUrl = `${environment.apiUrl}/ordens-de-servico`;

  constructor(http: HttpClient) {
    super(http);
  }
}