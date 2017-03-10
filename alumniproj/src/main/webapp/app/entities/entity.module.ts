import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { AlumniprojAlumniModule } from './alumni/alumni.module';
import { AlumniprojAlumniPhotoModule } from './alumni-photo/alumni-photo.module';
import { AlumniprojAlumniPhotoCategoryModule } from './alumni-photo-category/alumni-photo-category.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        AlumniprojAlumniModule,
        AlumniprojAlumniPhotoModule,
        AlumniprojAlumniPhotoCategoryModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AlumniprojEntityModule {}
