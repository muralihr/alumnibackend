import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { AlumniComponent } from './alumni.component';
import { AlumniDetailComponent } from './alumni-detail.component';
import { AlumniPopupComponent } from './alumni-dialog.component';
import { AlumniDeletePopupComponent } from './alumni-delete-dialog.component';

import { Principal } from '../../shared';

@Injectable()
export class AlumniResolvePagingParams implements Resolve<any> {

  constructor(private paginationUtil: PaginationUtil) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
      let page = route.queryParams['page'] ? route.queryParams['page'] : '1';
      let sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
      return {
          page: this.paginationUtil.parsePage(page),
          predicate: this.paginationUtil.parsePredicate(sort),
          ascending: this.paginationUtil.parseAscending(sort)
    };
  }
}

export const alumniRoute: Routes = [
  {
    path: 'alumni',
    component: AlumniComponent,
    resolve: {
      'pagingParams': AlumniResolvePagingParams
    },
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'Alumni'
    }
  }, {
    path: 'alumni/:id',
    component: AlumniDetailComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'Alumni'
    }
  }
];

export const alumniPopupRoute: Routes = [
  {
    path: 'alumni-new',
    component: AlumniPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'Alumni'
    },
    outlet: 'popup'
  },
  {
    path: 'alumni/:id/edit',
    component: AlumniPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'Alumni'
    },
    outlet: 'popup'
  },
  {
    path: 'alumni/:id/delete',
    component: AlumniDeletePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'Alumni'
    },
    outlet: 'popup'
  }
];
