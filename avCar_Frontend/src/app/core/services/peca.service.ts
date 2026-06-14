import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CrudService } from './crud.service';
import { Peca } from '../models/peca.model';
import { environment } from '../../../environments/environment';

@Injectable({ providedIn: 'root' })
export class PecaService extends CrudService<Peca> {
  protected apiUrl = `${environment.apiUrl}/pecas`;

  constructor(http: HttpClient) {
    super(http);
  }
}