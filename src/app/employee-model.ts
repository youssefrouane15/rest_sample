import {ClientModel} from './client-model'
import * as moment from 'moment'

export class EmployeeModel {
    constructor(
        public id: string,
        public firstName: string,
        public lastName: string,
        public birthDate: string,
        public birthDateFormatted: moment.Moment,
        public technologies: string,
        public currentPosition : string,
        public client: ClientModel
){}
}
