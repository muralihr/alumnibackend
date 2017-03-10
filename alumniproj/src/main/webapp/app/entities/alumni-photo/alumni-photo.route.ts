import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { AlumniPhotoComponent } from './alumni-photo.component';
import { AlumniPhotoDetailComponent } from './alumni-photo-detail.component';
import { AlumniPhotoPopupComponent } from './alumni-photo-dialog.component';
import { AlumniPhotoDeletePopupComponent } from './alumni-photo-delete-dialog.component';

import { Principal } from '../../shared';

@Injectable()
export class AlumniPhotoResolvePagingParams implements Resolve<any> {

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

export const alumniPhotoRoute: Routes = [
  {
    path: 'alumni-photo',
    component: AlumniPhotoComponent,
    resolve: {
      'pagingParams': AlumniPhotoResolvePagingParams
    },
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'AlumniPhotos'
    }
  }, {
    path: 'alumni-photo/:id',
    component: AlumniPhotoDetailComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'AlumniPhotos'
    }
  }
];

export const alumniPhotoPopupRoute: Routes = [
  {
    path: 'alumni-photo-new',
    component: AlumniPhotoPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'AlumniPhotos'
    },
    outlet: 'popup'
  },
  {
    path: 'alumni-photo/:id/edit',
    component: AlumniPhotoPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'AlumniPhotos'
    },
    outlet: 'popup'
  },
  {
    path: 'alumni-photo/:id/delete',
    component: AlumniPhotoDeletePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'AlumniPhotos'
    },
    outlet: 'popup'
  }
];
