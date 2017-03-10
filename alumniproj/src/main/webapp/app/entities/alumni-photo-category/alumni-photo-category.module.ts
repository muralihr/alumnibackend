import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AlumniprojSharedModule } from '../../shared';

import {
    AlumniPhotoCategoryService,
    AlumniPhotoCategoryPopupService,
    AlumniPhotoCategoryComponent,
    AlumniPhotoCategoryDetailComponent,
    AlumniPhotoCategoryDialogComponent,
    AlumniPhotoCategoryPopupComponent,
    AlumniPhotoCategoryDeletePopupComponent,
    AlumniPhotoCategoryDeleteDialogComponent,
    alumniPhotoCategoryRoute,
    alumniPhotoCategoryPopupRoute,
    AlumniPhotoCategoryResolvePagingParams,
} from './';

let ENTITY_STATES = [
    ...alumniPhotoCategoryRoute,
    ...alumniPhotoCategoryPopupRoute,
];

@NgModule({
    imports: [
        AlumniprojSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        AlumniPhotoCategoryComponent,
        AlumniPhotoCategoryDetailComponent,
        AlumniPhotoCategoryDialogComponent,
        AlumniPhotoCategoryDeleteDialogComponent,
        AlumniPhotoCategoryPopupComponent,
        AlumniPhotoCategoryDeletePopupComponent,
    ],
    entryComponents: [
        AlumniPhotoCategoryComponent,
        AlumniPhotoCategoryDialogComponent,
        AlumniPhotoCategoryPopupComponent,
        AlumniPhotoCategoryDeleteDialogComponent,
        AlumniPhotoCategoryDeletePopupComponent,
    ],
    providers: [
        AlumniPhotoCategoryService,
        AlumniPhotoCategoryPopupService,
        AlumniPhotoCategoryResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AlumniprojAlumniPhotoCategoryModule {}
