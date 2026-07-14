import { MovieDetails } from './movie-details.model';
import { Actor } from './actor.model';

export interface Movie {
  id?: string;
  title: string;
  year: number;
  director: string;
  genres: string[];
  details:MovieDetails;
  actors?:Actor[];
}
