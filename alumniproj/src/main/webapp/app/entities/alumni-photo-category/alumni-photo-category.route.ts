import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { AlumniPhotoCategoryComponent } from './alumni-photo-category.component';
import { AlumniPhotoCategoryDetailComponent } from './alumni-photo-category-detail.component';
import { AlumniPhotoCategoryPopupComponent } from './alumni-photo-category-dialog.component';
import { AlumniPhotoCategoryDeletePopupComponent } from './alumni-photo-category-delete-dialog.component';

import { Principal } from '../../shared';

@Injectable()
export class AlumniPhotoCategoryResolvePagingParams implements Resolve<any> {

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

export const alumniPhotoCategoryRoute: Routes = [
  {
    path: 'alumni-photo-category',
    component: AlumniPhotoCategoryComponent,
    resolve: {
      'pagingParams': AlumniPhotoCategoryResolvePagingParams
    },
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'AlumniPhotoCategories'
    }
  }, {
    path: 'alumni-photo-category/:id',
    component: AlumniPhotoCategoryDetailComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'AlumniPhotoCategories'
    }
  }
];

export const alumniPhotoCategoryPopupRoute: Routes = [
  {
    path: 'alumni-photo-category-new',
    component: AlumniPhotoCategoryPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'AlumniPhotoCategories'
    },
    outlet: 'popup'
  },
  {
    path: 'alumni-photo-category/:id/edit',
    component: AlumniPhotoCategoryPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'AlumniPhotoCategories'
    },
    outlet: 'popup'
  },
  {
    path: 'alumni-photo-category/:id/delete',
    component: AlumniPhotoCategoryDeletePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'AlumniPhotoCategories'
    },
    outlet: 'popup'
  }
];
