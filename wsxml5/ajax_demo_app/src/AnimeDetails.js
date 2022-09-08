import React from 'react';
import './AnimeDetails.css';

export default class AnimeDetails extends React.Component {

    constructor(props = {}) {
        super(props)
    }

    render() {
        const anime = this.props.anime
        return (
            <div className='AnimeDetails'>
                <div className='NavbarLine'>
                    <button className='CloseDetails'
                    onClick={this.props.closeAnimeDetails}>&times;</button>
                </div>
                <div>
                    <img src={anime.images.webp.large_image_url} />
                </div>
                <div>
                    <h1>{anime.title}</h1>
                    <h2>{anime.title_japanese} / {anime.title_english}</h2>
                    <p>
                        <a href={anime.url} target="_blank">MyAnimeList</a>
                    </p>
                    <p><b>Score:</b> {anime.score} by {anime.scored_by} member(s).</p>
                    <p><b>Rank:</b> {anime.rank} / <b>Popularity:</b> {anime.popularity}</p>
                    <h3>Synopsis</h3>
                    <p><button>View synopsis</button></p>
                    <div>{anime.synopsis}</div>
                </div>
            </div>
        )
    }
}