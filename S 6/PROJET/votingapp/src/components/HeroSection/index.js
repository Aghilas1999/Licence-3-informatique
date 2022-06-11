import React, { useState } from 'react';
import { Button } from '../ButtonElements';
import { ButtonForRedirect } from '../Button';

import Video from '../../videos/video.mp4';
import {
  HeroContainer,
  HeroBg,
  VideoBg,
  HeroContent,
  HeroH1,
  HeroP,
  HeroBtnWrapper,
  ArrowForward,
  ArrowRight
} from './HeroElements';


function HeroSection() {
  const [hover, setHover] = useState(false);

  const onHover = () => {
    setHover(!hover);
  };
  return (
    <HeroContainer id='home'>
      <HeroBg>
        <VideoBg playsInline autoPlay loop muted src={Video} type='video/mp4' />
      </HeroBg>
      <HeroContent>
        <HeroH1>La solution électorale parfaite </HeroH1>
        <HeroP>
          i-voting est une plateforme de vote en ligne qui vous aide a gérer vos élections facilement et en toute sécurité grace a la technologie de la blockchain
        </HeroP>
        <HeroBtnWrapper>
          <ButtonForRedirect to='/organisation'
            smooth={true}
            duration={500}
            spy={true}
            exact='true'
            offset={-80}
            primary='true'
            dark='true'
            onMouseEnter={onHover}
            onMouseLeave={onHover}
          >
            Administrateur  {hover ? <ArrowForward /> : <ArrowRight />}
          </ButtonForRedirect>
        </HeroBtnWrapper>
        <HeroBtnWrapper>
          <ButtonForRedirect to='/candidate'
            smooth={true}
            duration={500}
            spy={true}
            exact='true'
            offset={-80}
            primary='true'
            dark='true'
            onMouseEnter={onHover}
            onMouseLeave={onHover}
          >
            Electeur  {hover ? <ArrowForward /> : <ArrowRight />}
          </ButtonForRedirect>
        </HeroBtnWrapper>
      </HeroContent>
    </HeroContainer>
  );
}

export default HeroSection;
