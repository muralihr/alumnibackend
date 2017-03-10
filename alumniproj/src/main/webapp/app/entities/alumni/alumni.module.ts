import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AlumniprojSharedModule } from '../../shared';
import { AlumniprojAdminModule } from '../../admin/admin.module';

import {
    AlumniService,
    AlumniPopupService,
    AlumniComponent,
    AlumniDetailComponent,
    AlumniDialogComponent,
    AlumniPopupComponent,
    AlumniDeletePopupComponent,
    AlumniDeleteDialogComponent,
    alumniRoute,
    alumniPopupRoute,
    AlumniResolvePagingParams,
} from './';

let ENTITY_STATES = [
    ...alumniRoute,
    ...alumniPopupRoute,
];

@NgModule({
    imports: [
        AlumniprojSharedModule,
        AlumniprojAdminModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        AlumniComponent,
        AlumniDetailComponent,
        AlumniDialogComponent,
        AlumniDeleteDialogComponent,
        AlumniPopupComponent,
        AlumniDeletePopupComponent,
    ],
    entryComponents: [
        AlumniComponent,
        AlumniDialogComponent,
        AlumniPopupComponent,
        AlumniDeleteDialogComponent,
        AlumniDeletePopupComponent,
    ],
    providers: [
        AlumniService,
        AlumniPopupService,
        AlumniResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AlumniprojAlumniModule {}
