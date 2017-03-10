export class AlumniPhoto {
    constructor(
        public id?: number,
        public title?: string,
        public description?: string,
        public address?: string,
        public latitude?: number,
        public longitude?: number,
        public mediaType?: number,
        public mediaFile?: any,
        public urlOrfileLink?: string,
        public userAgent?: string,
        public uploadTime?: any,
        public alumniuserId?: number,
    ) { }
}
