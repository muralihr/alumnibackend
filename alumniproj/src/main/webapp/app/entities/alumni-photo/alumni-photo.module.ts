import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AlumniprojSharedModule } from '../../shared';
import { AlumniprojAdminModule } from '../../admin/admin.module';

import {
    AlumniPhotoService,
    AlumniPhotoPopupService,
    AlumniPhotoComponent,
    AlumniPhotoDetailComponent,
    AlumniPhotoDialogComponent,
    AlumniPhotoPopupComponent,
    AlumniPhotoDeletePopupComponent,
    AlumniPhotoDeleteDialogComponent,
    alumniPhotoRoute,
    alumniPhotoPopupRoute,
    AlumniPhotoResolvePagingParams,
} from './';

let ENTITY_STATES = [
    ...alumniPhotoRoute,
    ...alumniPhotoPopupRoute,
];

@NgModule({
    imports: [
        AlumniprojSharedModule,
        AlumniprojAdminModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        AlumniPhotoComponent,
        AlumniPhotoDetailComponent,
        AlumniPhotoDialogComponent,
        AlumniPhotoDeleteDialogComponent,
        AlumniPhotoPopupComponent,
        AlumniPhotoDeletePopupComponent,
    ],
    entryComponents: [
        AlumniPhotoComponent,
        AlumniPhotoDialogComponent,
        AlumniPhotoPopupComponent,
        AlumniPhotoDeleteDialogComponent,
        AlumniPhotoDeletePopupComponent,
    ],
    providers: [
        AlumniPhotoService,
        AlumniPhotoPopupService,
        AlumniPhotoResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AlumniprojAlumniPhotoModule {}
