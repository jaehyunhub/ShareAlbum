// components/ImageButton.tsx
import Image from 'next/image';
import { FC, MouseEventHandler } from 'react';

interface ImageButtonProps {
  src: string;
  alt: string;
  onClick: MouseEventHandler<HTMLButtonElement>;
}

const ImageButton: FC<ImageButtonProps> = ({ src, alt, onClick }) => {
  return (
    <button
      onClick={onClick}
      className="inline-block mr-2 text-2xl"
    >
      <Image src={src} alt={alt} width={270} height={10} className="rounded-md" />
    </button>
  );
};
export default ImageButton;